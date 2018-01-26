package com.pycogroup.taotran.service;

import com.pycogroup.taotran.entity.IDocument;

import java.util.List;

public interface DocumentService<T extends IDocument> {

    List<T> findAll();

    T findOne(String id);

    T save(T t);

    void delete(T t);

    void delete(String id);

}
