package com.pycogroup.taotran.config.mongo;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.MongoMappingEvent;

public class MongoEventListener<E> extends AbstractMongoEventListener<MongoMappingEvent<E>> {


}
