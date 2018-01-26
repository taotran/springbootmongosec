package com.pycogroup.taotran.service.user;

import com.pycogroup.taotran.entity.User;
import com.pycogroup.taotran.service.DocumentServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceBean extends DocumentServiceBean<User> implements UserService {

    private final MongoOperations operations;

    @Autowired
    public UserServiceBean(MongoOperations operations) {

        Assert.notNull(operations, "'operations' must not be null!");

        this.operations = operations;
    }

    @Override
    public List<User> findAll(int offset) {
        return new ArrayList<User>();
    }

    @Override
    public List<User> findSpecificAgeRange(int min, int max) {
        final Query query = new Query();

        query.addCriteria(Criteria.where("age").gte(min).lte(max));

        return operations.find(query, User.class);
    }
}
