package com.pycogroup.taotran.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@TestConfiguration
public class SpringSecurityWebTestConfig {

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        final User user = new User("admin", "admin123", authorities());
        return new InMemoryUserDetailsManager(Arrays.asList(user));
    }

    private List<GrantedAuthority> authorities() {
        final List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return authorities;
    }

//    @Bean("mockMvc")
//    public MockMvc assuredMockMvc() {
//        return RestAssuredMockMvc.mockMvc;
//    }
}
