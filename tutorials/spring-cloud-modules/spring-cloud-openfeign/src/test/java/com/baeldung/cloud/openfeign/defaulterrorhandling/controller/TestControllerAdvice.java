package com.baeldung.cloud.openfeign.defaulterrorhandling.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import feign.FeignException;

@RestControllerAdvice
public class TestControllerAdvice {

    @ExceptionHandler({FeignException.class})
    public ResponseEntity<Object> handleFeignException(FeignException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
