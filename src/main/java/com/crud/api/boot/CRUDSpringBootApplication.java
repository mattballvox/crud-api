
package com.crud.api.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.WebApplicationInitializer;

/**
 *
 * @author mball
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.crud.api.repository")
@EntityScan(basePackages = "com.crud.api.repository.model")
@ComponentScan(basePackages = "com.crud.api")
@EnableAsync
public class CRUDSpringBootApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CRUDSpringBootApplication.class);
    }

    public static void main(String[] args) throws Exception {

        SpringApplication.run(CRUDSpringBootApplication.class, args);
    }

}
