package com.pycogroup.taotran.service.auth;

import com.pycogroup.taotran.entity.User;
import com.pycogroup.taotran.entity.UserRole;
import com.pycogroup.taotran.service.DocumentService;

import java.util.List;

public interface UserRoleService extends DocumentService<UserRole> {

    List<UserRole> findAllByUser(User user);
}
