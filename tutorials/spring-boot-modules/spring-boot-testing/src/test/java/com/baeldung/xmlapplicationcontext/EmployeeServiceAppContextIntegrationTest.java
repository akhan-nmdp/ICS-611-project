package com.baeldung.xmlapplicationcontext;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baeldung.xmlapplicationcontext.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = XmlBeanApplication.class)
//@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/application-context.xml")
public class EmployeeServiceAppContextIntegrationTest {

    @Autowired
    private EmployeeService service;

    @Test
    public void whenContextLoads_thenServiceISNotNull() {
        assertThat(service).isNotNull();
    }

}
