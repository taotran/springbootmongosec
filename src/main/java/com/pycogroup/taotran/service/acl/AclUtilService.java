package com.pycogroup.taotran.service.acl;

import com.pycogroup.taotran.entity.AbstractDocument;
import org.springframework.security.core.Authentication;

public interface AclUtilService<T extends AbstractDocument> {

    void setOwnerRightForPersistenceObject(T persistedObject, Authentication authentication);
}
