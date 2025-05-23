package com.baeldung.overridebean.testbean;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.convention.TestBean;
import org.springframework.test.web.servlet.MockMvc;

import com.baeldung.overridebean.Config;
import com.baeldung.overridebean.Endpoint;
import com.baeldung.overridebean.Service;
import com.baeldung.overridebean.boot.Application;

@SpringBootTest(classes = { Application.class, Config.class, Endpoint.class })
@AutoConfigureMockMvc
class OverrideBeanDefinitionUsingTestBeanUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @TestBean
    private Service myFakeService;


    static Service myFakeService() {
        return new MyFakeService();
    }

    @Test
    void whenUsingTestBean_thenBeanGetsOverriden() throws Exception {
        this.mockMvc.perform(get("/hello"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Hi, there")));
    }
}

class MyFakeService implements Service {
    @Override
    public String helloWorld() {
        return "Hi, there";
    }
}