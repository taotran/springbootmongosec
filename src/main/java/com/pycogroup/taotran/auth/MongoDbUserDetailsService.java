package com.pycogroup.taotran.auth;

import com.pycogroup.taotran.entity.User;
import com.pycogroup.taotran.entity.UserRole;
import com.pycogroup.taotran.service.auth.UserRoleService;
import com.pycogroup.taotran.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Component("mongoDbUserDetailsService")
public class MongoDbUserDetailsService implements UserDetailsService {

    private final UserService userService;

    private final UserRoleService userRoleService;

    @Autowired
    public MongoDbUserDetailsService(UserService userService, UserRoleService userRoleService) {
        Assert.notNull(userService, "'userService' must not be null!");
        Assert.notNull(userRoleService, "'userRoleService' must not be null!");

        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s is not found", username));
        }

        final List<UserRole> userRoles = userRoleService.findAllByUser(user);

        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (UserRole userRole : userRoles) {
            authorities.add(new SimpleGrantedAuthority(userRole.getRole().getRole()));
        }
        user.setGrantedAuthorities(authorities);
        return user;
    }
}
