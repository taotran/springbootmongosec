package com.pycogroup.taotran.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pycogroup.taotran.constant.MappingPath;
import com.pycogroup.taotran.entity.User;
import com.pycogroup.taotran.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(MappingPath.USER)
public class UserResource extends BaseKafkaResource<User, com.pycogroup.taotran.springbootmongosec.avroentity.User> {

    private final UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public UserResource(UserService userService) {

        Assert.notNull(userService, "'userService' must not be null!");
        this.userService = userService;
    }

    @Override
    public void save(@RequestBody User user) {
        final User u = userService.save(user);


        final com.pycogroup.taotran.springbootmongosec.avroentity.User sendingUser =
                com.pycogroup.taotran.springbootmongosec.avroentity.User.newBuilder()
                        .setId(u.getId())
                        .setUsername(u.getUsername())
                        .setPassword(u.getPassword())
                        .setAge(u.getAge())
                        .setCredentialsNonExpired(u.isCredentialsNonExpired())
                        .setAccountNonExpired(u.isAccountNonExpired())
                        .setAccountNonLocked(u.isAccountNonLocked())
                        .setEnabled(u.isEnabled())
                        .setGrantedAuthorities(new ArrayList<>())
                        .build();

        sender.send(sendingUser);

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
