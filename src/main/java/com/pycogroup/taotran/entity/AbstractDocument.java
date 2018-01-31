package com.pycogroup.taotran.entity;

import org.springframework.data.annotation.Id;

public class AbstractDocument implements IDocument {

    @Id
    private String id;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
