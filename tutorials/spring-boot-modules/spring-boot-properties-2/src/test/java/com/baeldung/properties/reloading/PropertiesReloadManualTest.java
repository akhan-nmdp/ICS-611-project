package com.baeldung.properties.reloading;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileOutputStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.baeldung.properties.reloading.beans.ConfigurationPropertiesRefreshConfigBean;
import com.baeldung.properties.reloading.beans.EnvironmentConfigBean;
import com.baeldung.properties.reloading.beans.PropertiesConfigBean;
import com.baeldung.properties.reloading.beans.ValueRefreshConfigBean;

@SpringBootTest(classes = SpringBootPropertiesTestApplication.class)
public class PropertiesReloadManualTest {

    protected MockMvc mvc;

    protected long refreshDelay = 3000;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ValueRefreshConfigBean valueRefreshConfigBean;

    @Autowired
    ConfigurationPropertiesRefreshConfigBean configurationPropertiesRefreshConfigBean;

    @Autowired
    EnvironmentConfigBean environmentConfigBean;

    @Autowired
    PropertiesConfigBean propertiesConfigBean;

    @Autowired
    @Qualifier("singletonValueRefreshConfigBean")
    ValueRefreshConfigBean singletonValueRefreshConfigBean;


    @BeforeEach
    public void setUp() throws Exception {
        mvc = MockMvcBuilders
          .webAppContextSetup(webApplicationContext)
          .build();
        createConfig("extra.properties", "application.theme.color", "blue");
        createConfig("extra2.properties", "application.theme.background", "red");
        Thread.sleep(refreshDelay);
        callRefresh();
    }

    @AfterEach
    public void tearDown() throws Exception {
        createConfig("extra.properties", "application.theme.color", "blue");
        createConfig("extra2.properties", "application.theme.background", "red");
    }

    @Test
    public void givenEnvironmentReader_whenColorChanged_thenExpectChangeValue() throws Exception {
        assertEquals("blue", environmentConfigBean.getColor());

        createConfig("extra.properties", "application.theme.color", "red");
        Thread.sleep(refreshDelay);

        assertEquals("red", environmentConfigBean.getColor());
    }

    @Test
    public void givenEnvironmentReader_whenBackgroundChanged_thenExpectChangeValue() throws Exception {
        assertEquals("red", environmentConfigBean.getBackgroundColor());

        createConfig("extra2.properties", "application.theme.background", "blue");
        Thread.sleep(refreshDelay);

        assertEquals("blue", environmentConfigBean.getBackgroundColor());
    }

    @Test
    public void givenPropertiesReader_whenColorChanged_thenExpectChangeValue() throws Exception {
        assertEquals("blue", propertiesConfigBean.getColor());

        createConfig("extra.properties", "application.theme.color", "red");
        Thread.sleep(refreshDelay);

        assertEquals("red", propertiesConfigBean.getColor());
    }

    @Test
    public void givenRefreshScopedValueReader_whenColorChangedAndRefreshCalled_thenExpectChangeValue() throws Exception {
        assertEquals("blue", valueRefreshConfigBean.getColor());

        createConfig("extra.properties", "application.theme.color", "red");
        Thread.sleep(refreshDelay);

        assertEquals("blue", valueRefreshConfigBean.getColor());

        callRefresh();

        assertEquals("red", valueRefreshConfigBean.getColor());
    }

    @Test
    public void givenSingletonRefreshScopedValueReader_whenColorChangedAndRefreshCalled_thenExpectOldValue() throws Exception {

        assertEquals("blue", singletonValueRefreshConfigBean.getColor());

        createConfig("extra.properties", "application.theme.color", "red");
        Thread.sleep(refreshDelay);

        assertEquals("blue", singletonValueRefreshConfigBean.getColor());

        callRefresh();

        assertEquals("blue", singletonValueRefreshConfigBean.getColor());
    }

    @Test
    public void givenRefreshScopedConfigurationPropertiesReader_whenColorChangedAndRefreshCalled_thenExpectChangeValue() throws Exception {

        assertEquals("blue", configurationPropertiesRefreshConfigBean.getColor());

        createConfig("extra.properties", "application.theme.color", "red");
        Thread.sleep(refreshDelay);

        assertEquals("blue", configurationPropertiesRefreshConfigBean.getColor());

        callRefresh();

        assertEquals("red", configurationPropertiesRefreshConfigBean.getColor());
    }

    public void callRefresh() throws Exception {
        MvcResult mvcResult = mvc
          .perform(MockMvcRequestBuilders
            .post("/actuator/refresh")
            .accept(MediaType.APPLICATION_JSON_VALUE))
          .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        assertEquals(200, response.getStatus());
    }

    public void createConfig(String file, String key, String value) throws Exception {
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(String
          .format("%s=%s", key, value)
          .getBytes());
        fo.close();
    }
}
