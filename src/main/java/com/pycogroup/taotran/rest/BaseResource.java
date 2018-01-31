package com.pycogroup.taotran.rest;

import com.pycogroup.taotran.entity.IDocument;
import com.pycogroup.taotran.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public class BaseResource<T extends IDocument> {

    @Autowired
    protected DocumentService<T> documentService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostFilter("hasRole('ADMIN') or hasPermission(filterObject, 'READ') or hasPermission(filterObject, admin)")
    public List<T> findAll() {
        return documentService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody T t) {
        documentService.save(t);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam String id) {
        documentService.delete(id);
    }

}
