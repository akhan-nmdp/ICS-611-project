package com.baeldung.serenity.spring;

import static com.baeldung.serenity.spring.RandomNumberUtil.randomInt;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import com.baeldung.serenity.spring.steps.AdderServiceSteps;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.junit.spring.integration.SpringIntegrationMethodRule;
import net.thucydides.core.annotations.Steps;

/**
 * @author aiet
 */
@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = AdderService.class)
public class AdderMethodDirtiesContextInitWorkaroundIntegrationTest {

    @Steps
    private AdderServiceSteps adderServiceSteps;

    @Before
    public void init() {
        adderServiceSteps.givenBaseAndAdder(randomInt(), randomInt());
    }

    @Test
    public void _1_givenNumber_whenAdd_thenSumWrong() {
        adderServiceSteps.whenAdd();
        adderServiceSteps.summedUp();
    }

    @Rule
    public SpringIntegrationMethodRule springIntegration = new SpringIntegrationMethodRule();

    @DirtiesContext
    @Test
    public void _0_givenNumber_whenAddAndAccumulate_thenSummedUp() {
        adderServiceSteps.whenAccumulate();
        adderServiceSteps.summedUp();

        adderServiceSteps.whenAdd();
        adderServiceSteps.sumWrong();
    }

}
