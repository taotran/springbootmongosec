package com.pycogroup.taotran.repository;

import com.pycogroup.taotran.entity.User;
import com.pycogroup.taotran.entity.UserRole;

import java.util.List;

public interface UserRoleRepository extends DocumentRepository<UserRole> {

    List<UserRole> findAllByUser(User user);
}
