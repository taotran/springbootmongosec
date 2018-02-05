package com.pycogroup.taotran.entity;

import org.mongodb.morphia.annotations.PrePersist;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

public class AbstractDocument implements IDocument {

    @Id
    private String id;

    @Field
    private Date createdDate;

    @Field
    private Date updatedDate;

    protected AbstractDocument() {

    }

    @PrePersist
    public void prePersist() {
        this.createdDate = new Date();
    }


    public void preUpdate() {
        this.updatedDate = new Date();
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }
}
