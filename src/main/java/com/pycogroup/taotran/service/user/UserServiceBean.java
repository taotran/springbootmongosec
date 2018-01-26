package com.pycogroup.taotran.service.user;

import com.pycogroup.taotran.entity.User;
import com.pycogroup.taotran.service.DocumentServiceBean;
import org.springframework.stereotype.Service;

@Service
public class UserServiceBean extends DocumentServiceBean<User> implements UserService {

}
