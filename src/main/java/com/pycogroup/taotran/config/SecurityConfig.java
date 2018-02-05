package com.pycogroup.taotran.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BasicAuthenticationEntryPoint basicAuthenticationEntryPoint;

    @Autowired
    @Qualifier("mongoDbUserDetailsService")
    private UserDetailsService mongoDbUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
//                .antMatchers("/").permitAll()
//                .regexMatchers("/user/^(.*?(\b?projection=\b)[^$]*)$").hasRole("SUPER_ADMIN")
//                .antMatchers("/user/**{\\d+}?projection=*").access("hasRole(SUPER_ADMIN)")
//                .antMatchers("/api/v1/**").authenticated()
                .anyRequest().authenticated()

        ;
        http.httpBasic().authenticationEntryPoint(basicAuthenticationEntryPoint);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(mongoDbUserDetailsService);
    }
}
