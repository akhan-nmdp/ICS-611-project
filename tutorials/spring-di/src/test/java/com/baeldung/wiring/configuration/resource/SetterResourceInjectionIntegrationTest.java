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
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
  classes = ApplicationContextTestResourceNameType.class)
public class SetterResourceInjectionIntegrationTest {

    private File defaultFile;

    @Resource
    protected void setDefaultFile(File defaultFile) {
        this.defaultFile = defaultFile;
    }

    @Test
    public void givenResourceAnnotation_WhenOnSetter_THEN_MUST_INJECT_Dependency() {
        assertNotNull(defaultFile);
        assertEquals("namedFile.txt", defaultFile.getName());
    }
}
