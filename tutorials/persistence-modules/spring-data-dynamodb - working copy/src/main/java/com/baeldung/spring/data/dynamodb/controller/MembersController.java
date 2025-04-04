package com.baeldung.spring.data.dynamodb.controller;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.baeldung.spring.data.dynamodb.model.Members;
import com.baeldung.spring.data.dynamodb.model.ProductInfo;
import com.baeldung.spring.data.dynamodb.repositories.MembersRepository;
import com.baeldung.spring.data.dynamodb.repositories.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000/")
public class MembersController {
    @Autowired
    MembersRepository membersRepository;

    @GetMapping("/members")
    public ResponseEntity<Iterable<Members>> getAll() {
        Iterable<Members> list = membersRepository.findAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(list);
    }
}
