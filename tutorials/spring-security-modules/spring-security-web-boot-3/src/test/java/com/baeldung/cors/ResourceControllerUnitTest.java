package com.baeldung.cors;

import com.baeldung.cors.basicauth.SpringBootSecurityApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import de.flapdoodle.embed.mongo.spring.autoconfigure.EmbeddedMongoAutoConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SpringBootSecurityApplication.class })
@EnableAutoConfiguration(exclude = { EmbeddedMongoAutoConfiguration.class})
public class ResourceControllerUnitTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
            .apply(SecurityMockMvcConfigurers.springSecurity())
            .build();
    }

    @Test
    public void givenPreFlightRequest_whenPerfomed_shouldReturnOK() throws Exception {
        mockMvc.perform(options("/user")
            .header("Access-Control-Request-Method", "GET")
            .header("Origin", "http://localhost:4200"))
            .andExpect(status().isOk());
    }
}
