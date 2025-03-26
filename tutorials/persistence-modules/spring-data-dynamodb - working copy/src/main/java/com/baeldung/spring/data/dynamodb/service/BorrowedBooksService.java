package com.baeldung.spring.data.dynamodb.service;

import com.baeldung.spring.data.dynamodb.model.BorrowedBooks;
import com.baeldung.spring.data.dynamodb.request.dto.BookReservation;

public interface BorrowedBooksService {

    Iterable<BorrowedBooks> getAll();
    BorrowedBooks get(String id);
    void reserve(BookReservation bookReservation);
}
