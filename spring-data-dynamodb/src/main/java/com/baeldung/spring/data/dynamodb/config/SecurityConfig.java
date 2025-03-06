package com.baeldung.spring.data.dynamodb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().antMatchers(HttpMethod.GET,"/productInfo").permitAll()
                .antMatchers(HttpMethod.POST, "/productInfo").permitAll()
                .anyRequest().authenticated();
        return http.build();
    }
}
