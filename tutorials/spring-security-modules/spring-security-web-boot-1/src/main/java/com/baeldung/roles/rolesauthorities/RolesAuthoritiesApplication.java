package com.baeldung.roles.rolesauthorities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan("com.baeldung.roles.rolesauthorities")
public class RolesAuthoritiesApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
    	System.setProperty("spring.profiles.default", "rolesauthorities");
        SpringApplication.run(RolesAuthoritiesApplication.class, args);
    }
}
