package com.baeldung.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baeldung.dto.BooleanObject;
import com.baeldung.dto.Employee;
import com.baeldung.service.ValidationService;

import jakarta.validation.Valid;

@RestController
public class ValidationController {

    @Autowired
    ValidationService service;

    @PostMapping("/validateBoolean")
    public ResponseEntity<String> processBooleanObject(@RequestBody @Valid BooleanObject booleanObj) {
        return ResponseEntity.ok("BooleanObject is valid");
    }

    @PostMapping("/validateBooleanAtService")
    public ResponseEntity<String> processBooleanObjectAtService() {
        BooleanObject boolObj = new BooleanObject();
        boolObj.setBoolField(Boolean.TRUE);
        boolObj.setTrueField(Boolean.FALSE);
        service.processBoolean(boolObj);
        return ResponseEntity.ok("BooleanObject is valid");
    }

    @PostMapping("/validateListAtService")
    public ResponseEntity<String> validateRoles(@RequestBody @Validated Employee request) {
        return ResponseEntity.ok("Roles are valid!");
    }
}
