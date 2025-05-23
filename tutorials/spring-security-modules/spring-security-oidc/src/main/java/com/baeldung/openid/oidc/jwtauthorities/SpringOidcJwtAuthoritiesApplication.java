/**
 * 
 */
package com.baeldung.openid.oidc.jwtauthorities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import com.baeldung.openid.oidc.discovery.SpringOidcDiscoveryApplication;
import com.baeldung.openid.oidc.utils.YamlLoaderInitializer;

@SpringBootApplication
public class SpringOidcJwtAuthoritiesApplication {
    
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringOidcJwtAuthoritiesApplication.class);
        ApplicationContextInitializer<ConfigurableApplicationContext> yamlInitializer = new YamlLoaderInitializer("jwtauthorities-application.yml");
        application.addInitializers(yamlInitializer);
        application.run(args);
    }
}
