package com.baeldung.spring.data.dynamodb.service;

public interface BookService {

    public void updateAvailability(String bookId, boolean availableInd);
}
