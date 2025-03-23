package com.baeldung.spring.data.dynamodb.service.impl;

import com.baeldung.spring.data.dynamodb.model.BorrowedBooks;
import com.baeldung.spring.data.dynamodb.repositories.BorrowedBooksRepository;
import com.baeldung.spring.data.dynamodb.request.dto.BookReservation;
import com.baeldung.spring.data.dynamodb.service.BorrowedBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Transactional
@Service
public class BorrowedBooksServiceImpl implements BorrowedBooksService {

    @Autowired
    BorrowedBooksRepository borrowedBooksRepository;


    @Override
    public Iterable<BorrowedBooks> getAll() {
        return borrowedBooksRepository.findAll();
    }

    @Override
    public BorrowedBooks get(String id) {
        Optional<BorrowedBooks> borrowedBook = borrowedBooksRepository.findById(id);
        if (!borrowedBook.isPresent()) {
            throw new RuntimeException("Unable to find borrowed book by: ".concat(id));
        }
        return borrowedBook.get();
    }

    @Override
    public void reserve(BookReservation bookReservation) {
        BorrowedBooks borrowedBooks = new BorrowedBooks();
        borrowedBooks.setBookId(bookReservation.getBookId());
        borrowedBooks.setMemberId(bookReservation.getMemberId());
        borrowedBooks.setBorrowDate(bookReservation.getBorrowDate());
        borrowedBooks.setReturnDate(LocalDate.now().plusWeeks(2).toString());
        borrowedBooksRepository.save(borrowedBooks);
    }
}
