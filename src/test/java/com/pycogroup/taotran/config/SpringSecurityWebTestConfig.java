package com.pycogroup.taotran.config;

import com.pycogroup.taotran.entity.User;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

@TestConfiguration
public class SpringSecurityWebTestConfig {

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        final User user = new User("admin", "admin123", new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new InMemoryUserDetailsManager(Arrays.asList(user));
    }
}
