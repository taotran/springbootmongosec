package com.pycogroup.taotran.repository;

import com.pycogroup.taotran.entity.User;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface UserRepository extends DocumentRepository<User>, QueryDslPredicateExecutor<User> {

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);
}
