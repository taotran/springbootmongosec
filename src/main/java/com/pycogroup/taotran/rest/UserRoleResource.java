package com.pycogroup.taotran.rest;

import com.pycogroup.taotran.entity.UserRole;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/userroles")
public class UserRoleResource extends BaseResource<UserRole> {

}
