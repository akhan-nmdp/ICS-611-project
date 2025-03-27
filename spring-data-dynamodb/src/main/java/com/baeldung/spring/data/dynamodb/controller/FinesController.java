package com.baeldung.spring.data.dynamodb.controller;

import com.baeldung.spring.data.dynamodb.model.Books;
import com.baeldung.spring.data.dynamodb.model.Fines;
import com.baeldung.spring.data.dynamodb.repositories.BooksRepository;
import com.baeldung.spring.data.dynamodb.repositories.FinesRepository;
import com.baeldung.spring.data.dynamodb.response.dto.FinesDto;
import com.baeldung.spring.data.dynamodb.service.FinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
public class FinesController {
    @Autowired
    FinesService finesService;

    @GetMapping("/fines")
    public ResponseEntity<List<FinesDto>> getAll() {
        List<FinesDto> list = finesService.getAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(list);
    }
}
