package com.baeldung.openid.oidc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private OAuth2RestTemplate restTemplate;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
            .antMatchers("/resources/**");
    }

    @Bean
    public OpenIdConnectFilter myFilter() {
        final OpenIdConnectFilter filter = new OpenIdConnectFilter("/google-login");
        filter.setRestTemplate(restTemplate);
        return filter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.addFilterAfter(new OAuth2ClientContextFilter(), AbstractPreAuthenticatedProcessingFilter.class)
            .addFilterAfter(myFilter(), OAuth2ClientContextFilter.class)
            .httpBasic()
            .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/google-login"))
            .and()
            .authorizeRequests()
            // .antMatchers("/","/index*").permitAll()
            .anyRequest()
            .authenticated();
        return http.build();
    }
}