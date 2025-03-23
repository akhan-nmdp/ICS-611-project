package com.baeldung.spring.data.dynamodb.controller;

import com.baeldung.spring.data.dynamodb.model.Librarians;
import com.baeldung.spring.data.dynamodb.repositories.LibrariansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000/")
public class LibrariansController {
    @Autowired
    LibrariansRepository librariansRepository;

    @GetMapping("/librarians")
    public ResponseEntity<Iterable<Librarians>> getAll() {
        Iterable<Librarians> list = librariansRepository.findAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(list);
    }

}
