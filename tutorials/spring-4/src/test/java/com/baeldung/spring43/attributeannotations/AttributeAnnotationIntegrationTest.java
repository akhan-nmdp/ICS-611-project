package com.baeldung.spring43.attributeannotations;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = AttributeAnnotationConfiguration.class)
@WebAppConfiguration
public class AttributeAnnotationIntegrationTest extends AbstractJUnit4SpringContextTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenInterceptorAddsRequestAndSessionParams_thenParamsInjectedWithAttributesAnnotations() throws Exception {
        String result = this.mockMvc.perform(get("/test").accept(MediaType.ALL)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        Assert.assertEquals("login = john, query = invoices", result);
    }

}
