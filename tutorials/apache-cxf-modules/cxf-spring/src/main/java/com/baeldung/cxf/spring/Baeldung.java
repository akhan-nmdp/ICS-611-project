package com.baeldung.cxf.spring;

import jakarta.jws.WebService;

@WebService
public interface Baeldung {
    String hello(String name);

    String register(Student student);
}