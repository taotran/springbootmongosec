package com.pycogroup.taotran.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BasicAuthenticationEntryPoint basicAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService mongoDbUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/api/v1/**").permitAll() //TODO: remember to remove this one
                .anyRequest().authenticated();
        http.httpBasic().authenticationEntryPoint(basicAuthenticationEntryPoint);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("admin").roles("ADMIN")
//                .and()
//                .withUser("user").password("user").roles("USER")
//        ;

        auth.userDetailsService(mongoDbUserDetailsService);
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.pycogroup.taotran.rest"))
                .paths(PathSelectors.regex("/api/v1/*.*"))
                .build()
                .apiInfo(metaData())
                ;
    }

    private ApiInfo metaData() {
        return new ApiInfo(
                "SpringBoot MongoDB",
                "SpringBoot MongoDB Demostration",
                "1.0",
                "Terms of Service",
                new Contact(
                        "Tao Tran",
                        "http://www.pycogroup.com",
                        "tao.tran@pycogroup.com"
                ),
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0"

        );
    }
}
