package com.baeldung.spring.data.dynamodb.service;

import com.baeldung.spring.data.dynamodb.model.Fines;
import com.baeldung.spring.data.dynamodb.response.dto.FinesDto;

import java.util.List;

public interface FinesService {

    void create(String borrowId, String memberId);
    List<FinesDto> getAll();
    Fines get(String borrowId);
}
