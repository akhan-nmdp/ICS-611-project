package com.baeldung.roles.ip.web;

import java.util.List;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;

import com.baeldung.roles.custom.persistence.model.Foo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @Autowired
    @Qualifier("springSecurityFilterChain")
    private Filter springSecurityFilterChain;

    @GetMapping("/filters")
    @ResponseBody
    public void getFilters() {
        FilterChainProxy filterChainProxy = (FilterChainProxy) springSecurityFilterChain;
        List<SecurityFilterChain> list = filterChainProxy.getFilterChains();
        list.stream()
              .flatMap(chain -> chain.getFilters().stream())
              .forEach(filter -> System.out.println(filter.getClass()));
    }

    @GetMapping("/foos/{id}")
    @ResponseBody
    public Foo findById(@PathVariable final long id, HttpServletRequest request) {
        return new Foo("Sample");
    }
}
