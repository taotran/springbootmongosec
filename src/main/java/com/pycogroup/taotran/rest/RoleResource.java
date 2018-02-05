package com.pycogroup.taotran.rest;

import com.pycogroup.taotran.constant.MappingPath;
import com.pycogroup.taotran.entity.Role;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = MappingPath.ROLE)
public class RoleResource extends BaseResource<Role> {


}
