package com.pycogroup.taotran.config;

import com.pycogroup.taotran.custom.cascade.CascadeSaveMongoEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ApplicationConfig {

//    TODO: check [[java.sql.SQLException: No suitable driver found]] when using h2 Driver
//    @Bean(name = "h2servletRegistration")
//    public ServletRegistrationBean h2servletRegistration(){
//        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
//        registrationBean.addUrlMappings("/console/*");
//        return registrationBean;
//
//    }

    /* Swagger configuration */

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

    /* Custom beans */

    @Bean
    public CascadeSaveMongoEventListener cascadeSaveMongoEventListener() {
        return new CascadeSaveMongoEventListener();
    }


}
