package com.pycogroup.taotran.repository;

import com.pycogroup.taotran.entity.AbstractDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DocumentRepository<T extends AbstractDocument> extends MongoRepository<T, String> {
}
