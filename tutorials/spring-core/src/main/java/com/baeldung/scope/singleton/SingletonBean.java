package com.baeldung.scope.singleton;

import com.baeldung.scope.prototype.PrototypeBean;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalTime;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;

public class SingletonBean {

    private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PrototypeBean prototypeBean;

    public SingletonBean() {
        logger.info("Singleton instance created");
    }

    public PrototypeBean getPrototypeBean() {
        logger.info(String.valueOf(LocalTime.now()));
        return prototypeBean;
    }
}
