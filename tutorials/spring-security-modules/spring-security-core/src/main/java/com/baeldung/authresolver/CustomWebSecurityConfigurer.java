package com.baeldung.authresolver;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationConverter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class CustomWebSecurityConfigurer {

    public AuthenticationConverter authenticationConverter() {
        return new BasicAuthenticationConverter();
    }

    public AuthenticationManagerResolver<HttpServletRequest> resolver() {
        return request -> {
            if (request
              .getPathInfo()
              .startsWith("/employee")) {
                return employeesAuthenticationManager();
            }
            return customersAuthenticationManager();
        };
    }

    public AuthenticationManager customersAuthenticationManager() {
        return authentication -> {
            if (isCustomer(authentication)) {
                return new UsernamePasswordAuthenticationToken(
                  authentication.getPrincipal(),
                  authentication.getCredentials(),
                  Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                );
            }
            throw new UsernameNotFoundException(authentication
              .getPrincipal()
              .toString());
        };
    }

    private boolean isCustomer(Authentication authentication) {
        return (authentication
          .getPrincipal()
          .toString()
          .startsWith("customer"));
    }

    private boolean isEmployee(Authentication authentication) {
        return (authentication
          .getPrincipal()
          .toString()
          .startsWith("employee"));
    }

    private AuthenticationFilter authenticationFilter() {
        AuthenticationFilter filter = new AuthenticationFilter(
          resolver(), authenticationConverter());
        filter.setSuccessHandler((request, response, auth) -> {});
        return filter;
    }

    private AuthenticationManager employeesAuthenticationManager() {
        return authentication -> {
            if (isEmployee(authentication)) {
                return new UsernamePasswordAuthenticationToken(
                  authentication.getPrincipal(),
                  authentication.getCredentials(),
                  Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
                );
            }
            throw new UsernameNotFoundException(authentication
              .getPrincipal()
              .toString());
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(authenticationFilter(), BasicAuthenticationFilter.class);
        return http.build();
    }

}
