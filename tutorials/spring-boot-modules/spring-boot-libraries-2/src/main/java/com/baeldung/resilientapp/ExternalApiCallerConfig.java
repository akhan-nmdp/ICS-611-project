package com.baeldung.resilientapp;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ExternalApiCallerConfig {
@Bean
@Qualifier("default")
public RestTemplate restTemplate() {
    return new RestTemplateBuilder().rootUri("http://localhost:9090")
        .build();
}
}
