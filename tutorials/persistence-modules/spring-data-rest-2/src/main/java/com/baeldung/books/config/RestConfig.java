package com.baeldung.books.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.baeldung.books.models.WebsiteUser;
import com.baeldung.books.projections.CustomBook;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration repositoryRestConfiguration,
                                                     CorsRegistry cors) {
        ExposureConfiguration config = repositoryRestConfiguration.getExposureConfiguration();
        config.forDomainType(WebsiteUser.class).withItemExposure((metadata, httpMethods) -> httpMethods.disable(HttpMethod.PATCH));
        repositoryRestConfiguration.getProjectionConfiguration().addProjection(CustomBook.class);
    }
}
