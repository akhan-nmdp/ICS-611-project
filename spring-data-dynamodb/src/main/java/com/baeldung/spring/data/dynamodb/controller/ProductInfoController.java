package com.baeldung.spring.data.dynamodb.controller;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.baeldung.spring.data.dynamodb.model.ProductInfo;
import com.baeldung.spring.data.dynamodb.repositories.ProductInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductInfoController {
    @Autowired
    ProductInfoRepository productInfoRepository;
    @Autowired
    AmazonDynamoDB amazonDynamoDB;

    @GetMapping("/productInfo")
    public ResponseEntity<Iterable<ProductInfo>> getAll() {
//        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
//
//        CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(ProductInfo.class);
//
//        tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
//
//        amazonDynamoDB.createTable(tableRequest);
//        ProductInfo productInfo = new ProductInfo("1", "2");
//        productInfoRepository.save(productInfo);
        Iterable<ProductInfo> list = productInfoRepository.findAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(list);
    }

    @PostMapping("/productInfo")
    public ResponseEntity<Iterable<ProductInfo>> create() {

        return ResponseEntity.ok().build();
    }
}
