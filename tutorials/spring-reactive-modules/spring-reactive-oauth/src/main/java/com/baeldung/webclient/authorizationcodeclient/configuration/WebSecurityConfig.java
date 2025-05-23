package com.baeldung.webclient.authorizationcodeclient.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class WebSecurityConfig {
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(s-> s.anyExchange().authenticated())
            .oauth2Client(Customizer.withDefaults())
            .formLogin(Customizer.withDefaults());
        return http.build();
    }

}
