package com.baeldung.security.csrf;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.baeldung.security.spring.SecurityWithoutCsrfConfig;
import com.baeldung.spring.MvcConfig;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = { SecurityWithoutCsrfConfig.class, MvcConfig.class })
public class CsrfDisabledIntegrationTest extends CsrfAbstractIntegrationTest {

    @Test
    public void givenNotAuth_whenAddFoo_thenUnauthorized() throws Exception {
        // @formatter:off
        mvc
        .perform(post("/auth/foos")
        .contentType(MediaType.APPLICATION_JSON)
        .content(createFoo()))
        .andExpect(status().isUnauthorized());
        // @formatter:on
    }

    @Test
    public void givenAuth_whenAddFoo_thenCreated() throws Exception {
        // @formatter:off
        mvc
        .perform(post("/auth/foos")
        .contentType(MediaType.APPLICATION_JSON)
        .content(createFoo())
        .with(testUser()))
        .andExpect(status().isCreated());
        // @formatter:on
    }

}
