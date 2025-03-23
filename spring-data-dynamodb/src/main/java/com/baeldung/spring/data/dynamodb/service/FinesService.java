package com.baeldung.spring.data.dynamodb.service;

import com.baeldung.spring.data.dynamodb.model.Fines;

public interface FinesService {

    void create(String borrowId, String memberId);
    Iterable<Fines> getAll();
    Fines get(String borrowId);
}
