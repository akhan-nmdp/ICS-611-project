package com.baeldung.wiring.configuration.resource;

import com.baeldung.wiring.configuration.ApplicationContextTestResourceNameType;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
  loader = AnnotationConfigContextLoader.class,
  classes = ApplicationContextTestResourceNameType.class)
public class FieldResourceInjectionIntegrationTest {

    @Resource(name = "namedFile")
    private File defaultFile;

    @Test
    public void givenResourceAnnotation_WhenOnField_ThenDependencyValid() {
        assertNotNull(defaultFile);
        assertEquals("namedFile.txt", defaultFile.getName());
    }
}
