package com.pycogroup.taotran.service.acl;

import com.pycogroup.taotran.entity.AbstractDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.model.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class AclUtilServiceBean<T extends AbstractDocument> implements AclUtilService<T> {


    private final JdbcMutableAclService aclService;

    @Autowired
    public AclUtilServiceBean(JdbcMutableAclService aclService) {

        Assert.notNull(aclService, "'aclService' must not be null!");

        this.aclService = aclService;
    }

    @Override
    @Transactional
    public void setOwnerRightForPersistenceObject(T t, Authentication authentication) {

        ObjectIdentity objectIdentity = new ObjectIdentityImpl(t.getClass().getName(), t.getId());
        Sid sid = new PrincipalSid(authentication.getName());

        Permission permission = BasePermission.ADMINISTRATION;

        MutableAcl acl = null;
        try {
            acl = (MutableAcl) aclService.readAclById(objectIdentity);
        } catch (NotFoundException e) {
            acl = aclService.createAcl(objectIdentity);
        }

        acl.insertAce(acl.getEntries().size(), permission, sid, true);
        aclService.updateAcl(acl);

    }
}
