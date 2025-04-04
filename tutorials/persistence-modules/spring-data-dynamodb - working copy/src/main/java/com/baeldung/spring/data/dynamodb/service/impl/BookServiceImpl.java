package com.baeldung.spring.data.dynamodb.service.impl;

import com.baeldung.spring.data.dynamodb.model.Books;
import com.baeldung.spring.data.dynamodb.repositories.BooksRepository;
import com.baeldung.spring.data.dynamodb.scheduler.FinesScheduler;
import com.baeldung.spring.data.dynamodb.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);
    @Autowired
    BooksRepository booksRepository;

    @Override
    public void updateAvailability(String bookId, boolean availableInd) {
        Optional<Books> bookInDb = booksRepository.findById(bookId);
        if (bookInDb.isPresent()) {
            log.info("Updating flag {} for bookId {}", availableInd, bookId);
            bookInDb.get().setAvailability(availableInd);
            booksRepository.save(bookInDb.get());
        } else {
            log.info("No book found to update availableInd");
        }
    }
}
