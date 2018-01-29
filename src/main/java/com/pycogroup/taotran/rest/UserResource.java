package com.pycogroup.taotran.rest;

import com.pycogroup.taotran.entity.User;
import com.pycogroup.taotran.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserResource {

    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        Assert.notNull(userService, "'userService' must not be null!");
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping(path = "/age-range")
    public List<User> findByAgeRange(@RequestParam int min, @RequestParam int max) {
        return userService.findSpecificAgeRange(min, max);
    }

    @GetMapping("/{userId}")
    public HttpEntity<User> find(@PathVariable String userId) {
        User user = userService.findOne(userId);
//        user.add(linkTo(methodOn(UserResource.class)));
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody User user) {
        userService.save(user);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam String userId) {
        userService.delete(userId);
    }
}
