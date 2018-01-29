package com.pycogroup.taotran.service.auth;

import com.mysema.commons.lang.Assert;
import com.pycogroup.taotran.entity.User;
import com.pycogroup.taotran.entity.UserRole;
import com.pycogroup.taotran.repository.UserRoleRepository;
import com.pycogroup.taotran.service.DocumentServiceBean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceBean extends DocumentServiceBean<UserRole> implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceBean(UserRoleRepository userRoleRepository) {
        Assert.notNull(userRoleRepository, "'userRoleRepository' must not be nulll!");
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public List<UserRole> findAllByUser(User user) {
        return userRoleRepository.findAllByUser(user);
    }
}
