package com.baeldung.spring.data.dynamodb.service;

import com.baeldung.spring.data.dynamodb.model.BorrowedBooks;
import com.baeldung.spring.data.dynamodb.request.dto.BookReservation;
import com.baeldung.spring.data.dynamodb.response.dto.BorrowedBooksDto;

import java.util.List;

public interface BorrowedBooksService {

    List<BorrowedBooksDto> getAll();
    Iterable<BorrowedBooks> findAll();
    BorrowedBooks get(String id);
    void reserve(BookReservation bookReservation);
}
