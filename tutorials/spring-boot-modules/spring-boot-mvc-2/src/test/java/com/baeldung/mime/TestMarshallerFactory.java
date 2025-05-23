package com.baeldung.mime;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class TestMarshallerFactory implements FactoryBean<IMarshaller> {

    @Autowired
    private Environment env;

    public TestMarshallerFactory() {
        super();
    }

    // API

    @Override
    public IMarshaller getObject() {
        final String testMime = env.getProperty("test.mime");
        if (testMime != null) {
            switch (testMime) {
            case "json":
                return new JacksonMarshaller();
            case "xml":
                return new XStreamMarshaller();
            default:
                throw new IllegalStateException();
            }
        }

        return new JacksonMarshaller();
    }

    @Override
    public Class<IMarshaller> getObjectType() {
        return IMarshaller.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}