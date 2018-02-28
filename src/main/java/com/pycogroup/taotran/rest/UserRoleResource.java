package com.pycogroup.taotran.rest;

import com.pycogroup.taotran.constant.MappingPath;
import com.pycogroup.taotran.entity.UserRole;
import com.pycogroup.taotran.service.DocumentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(MappingPath.USER_ROLE)
public class UserRoleResource extends BaseResource<UserRole> {

    public UserRoleResource(DocumentService<UserRole> documentService) {
        super(documentService);
    }
}
