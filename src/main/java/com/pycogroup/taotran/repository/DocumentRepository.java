package com.pycogroup.taotran.repository;

import com.pycogroup.taotran.entity.AbstractDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DocumentRepository<T extends AbstractDocument> extends MongoRepository<T, String>, PagingAndSortingRepository<T, String> {
}
