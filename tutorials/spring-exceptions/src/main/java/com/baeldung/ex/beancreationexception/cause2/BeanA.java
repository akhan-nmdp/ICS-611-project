package com.baeldung.ex.beancreationexception.cause2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanA {

    @Autowired
    private IBeanB dependency;

}