package com.pycogroup.taotran.service.user;

import com.pycogroup.taotran.entity.QUser;
import com.pycogroup.taotran.entity.User;
import com.pycogroup.taotran.repository.UserRepository;
import com.pycogroup.taotran.service.DocumentServiceBean;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceBean extends DocumentServiceBean<User> implements UserService {

    private final MongoOperations operations;

    private final UserRepository userRepository;

    @Autowired
    public UserServiceBean(MongoOperations operations, UserRepository userRepository) {
        Assert.notNull(operations, "'operations' must not be null!");
        Assert.notNull(userRepository, "'userRepository' must not be null!");

        this.operations = operations;
        this.userRepository = userRepository;
    }


    @Override
    public User save(User user) {
        operations.save(user);
        return user;
    }

    @Override
    public List<User> findAll(int offset) {
        return new ArrayList<>();
    }

    @Override
    public List<User> findBySpecificAgeRange(int min, int max) {
        final Query query = new Query();

        query.addCriteria(Criteria.where("age").gte(min).lte(max));

        return operations.find(query, User.class);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> filterByUsername(String username, Sort sort, Pageable pageable) {

        final QUser qUser = new QUser("user");

        final Predicate predicate = qUser.username.contains(username);

        return userRepository.findAll(predicate, pageable).getContent();
    }

    @Override
    public boolean checkAccess(User checkingUser) {
        final UserDetails userDetails = (UserDetails) SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication().getPrincipal();
        return userDetails.getUsername().equalsIgnoreCase("admin") || userDetails.getUsername().equals(checkingUser.getUsername());
    }

    @Override
    public boolean checkAccess() {
        return true;
    }
}
