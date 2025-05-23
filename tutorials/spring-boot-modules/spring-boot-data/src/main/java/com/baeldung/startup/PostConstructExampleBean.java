package com.baeldung.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;

@Component
@Scope(value = "prototype")
public class PostConstructExampleBean {

    private static final Logger LOG = LoggerFactory.getLogger(PostConstructExampleBean.class);

    @Autowired
    private Environment environment;

    @PostConstruct
    public void init() {
        LOG.info("Env Default Profiles", Arrays.asList(environment.getDefaultProfiles()));
    }
}
