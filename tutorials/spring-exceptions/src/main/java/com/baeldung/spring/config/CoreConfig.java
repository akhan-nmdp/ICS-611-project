package com.baeldung.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("com.baeldung.core")
public class CoreConfig implements WebMvcConfigurer {

    public CoreConfig() {
        super();
    }

}