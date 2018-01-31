package com.pycogroup.taotran.service;

import com.pycogroup.taotran.entity.AbstractDocument;
import com.pycogroup.taotran.entity.IDocument;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface DocumentService<T extends AbstractDocument> {

    List<T> findAll();

    T findOne(String id);

    T save(T t, Authentication authentication);

    void delete(T t);

    void delete(String id);

}
