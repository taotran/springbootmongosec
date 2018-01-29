package com.pycogroup.taotran.service;

import com.pycogroup.taotran.entity.IDocument;
import com.pycogroup.taotran.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentServiceBean<T extends IDocument> implements DocumentService<T> {

    @Autowired
    private DocumentRepository<T> documentRepository;

    @Override
    public List<T> findAll() {
        return documentRepository.findAll();
    }

    @Override
    public T findOne(String id) {
        return documentRepository.findOne(id);
    }

    @Override
    @Transactional
    public T save(T t) {
        return documentRepository.save(t);
    }

    @Override
    public void delete(T t) {
        documentRepository.delete(t);
    }

    @Override
    public void delete(String id) {
        documentRepository.delete(id);
    }
}
