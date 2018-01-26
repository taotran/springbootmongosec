package com.pycogroup.taotran.service.user;

import com.pycogroup.taotran.entity.User;
import com.pycogroup.taotran.service.DocumentService;

import java.util.List;

public interface UserService extends DocumentService<User> {


    List<User> findAll(int offset);

    List<User> findSpecificAgeRange(int min, int max);

}
