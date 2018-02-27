package com.pycogroup.taotran.rest;

import com.pycogroup.taotran.constant.MappingPath;
import com.pycogroup.taotran.entity.User;
import com.pycogroup.taotran.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(MappingPath.USER)
public class UserResource extends BaseResource<User> {

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {

        Assert.notNull(userService, "'userService' must not be null!");
        this.userService = userService;
    }

    @GetMapping(path = "/age-range")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> findByAgeRange(@RequestParam int min, @RequestParam int max) {
        return userService.findBySpecificAgeRange(min, max);
    }

    @GetMapping(path = "/username")
    public List<User> filterUserByUsername(@RequestParam String username, Pageable pageable) {
        return userService.filterByUsername(username, null, pageable);
    }
}
