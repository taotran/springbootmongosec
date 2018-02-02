package com.pycogroup.taotran.service.acl;

import com.pycogroup.taotran.entity.AbstractDocument;

public interface AclUtilService<T extends AbstractDocument> {

    void setOwnerRightForPersistenceObject(T persistedObject);
}
