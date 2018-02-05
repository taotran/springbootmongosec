package com.pycogroup.taotran.repository;

import com.pycogroup.taotran.entity.User;
import com.pycogroup.taotran.entity.projection.UserDetailProjection;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(itemResourceRel = "user", collectionResourceRel = "user", path = "user", excerptProjection = UserDetailProjection.class)
public interface UserRepository extends DocumentRepository<User>, QueryDslPredicateExecutor<User> {

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);
}
