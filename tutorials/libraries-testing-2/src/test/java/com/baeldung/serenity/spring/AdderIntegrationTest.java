package com.baeldung.serenity.spring;

import org.jbehave.core.annotations.BeforeStory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import net.serenitybdd.jbehave.SerenityStory;

@ContextConfiguration(classes = { AdderController.class, AdderService.class })
public class AdderIntegrationTest extends SerenityStory {

    @Autowired
    private AdderService adderService;

    @BeforeStory
    public void init() {
        RestAssuredMockMvc.standaloneSetup(new AdderController(adderService));
    }

}
