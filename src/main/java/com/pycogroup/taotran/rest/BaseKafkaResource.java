package com.pycogroup.taotran.rest;

import com.pycogroup.taotran.entity.AbstractDocument;
import com.pycogroup.taotran.service.DocumentService;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseKafkaResource<T extends AbstractDocument, S extends SpecificRecordBase> extends BaseResource<T> {

    @Autowired
    protected AbstractKafkaSender<S> sender;

    @Autowired
    protected DocumentService<T> service;

}
