package com.pycogroup.taotran.rest;

import com.pycogroup.taotran.entity.User;
import com.pycogroup.taotran.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserResource extends BaseResource<User> {

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/age-range")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> findByAgeRange(@RequestParam int min, @RequestParam int max) {
        return userService.findSpecificAgeRange(min, max);
    }
}
