package com.baeldung.cucumber.tags.controller;


import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @RequestMapping(value="/status", produces= MediaType.APPLICATION_JSON_VALUE)
    public HttpStatusCode statusCheck() {
        return ResponseEntity.ok().build().getStatusCode();
    }
}
