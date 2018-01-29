package com.pycogroup.taotran.repository;

import com.pycogroup.taotran.entity.User;

public interface UserRepository extends DocumentRepository<User> {

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);
}
