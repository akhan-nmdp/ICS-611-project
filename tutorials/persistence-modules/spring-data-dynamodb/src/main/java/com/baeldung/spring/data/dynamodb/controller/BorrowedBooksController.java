package com.baeldung.spring.data.dynamodb.controller;

import com.baeldung.spring.data.dynamodb.model.BorrowedBooks;
import com.baeldung.spring.data.dynamodb.request.dto.BookReservation;
import com.baeldung.spring.data.dynamodb.response.dto.BorrowedBooksDto;
import com.baeldung.spring.data.dynamodb.service.BorrowedBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
public class BorrowedBooksController {
    @Autowired
    BorrowedBooksService borrowedBooksService;

    @GetMapping("/borrowed-books")
    public ResponseEntity<List<BorrowedBooksDto>> getAll() {
        List<BorrowedBooksDto> list = borrowedBooksService.getAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(list);
    }

    @PostMapping("/borrowed-books")
    public ResponseEntity<Void> reserve(@RequestBody BookReservation bookReservation) {
        borrowedBooksService.reserve(bookReservation);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/borrowed-books/return")
    public ResponseEntity<Void> returnBook(@RequestParam String borrowId) {
        borrowedBooksService.returnBook(borrowId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
