package com.baeldung.cloud.openfeign.defaulterrorhandling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baeldung.cloud.openfeign.defaulterrorhandling.client.ProductClient;
import com.baeldung.cloud.openfeign.defaulterrorhandling.model.Product;

@RestController("product_controller1")
@RequestMapping(value ="myapp1")
public class ProductController {

    private final ProductClient productClient;

    @Autowired
    public ProductController(ProductClient productClient) {
        this.productClient = productClient;
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable String id) {
        return productClient.getProduct(id);
    }
}
