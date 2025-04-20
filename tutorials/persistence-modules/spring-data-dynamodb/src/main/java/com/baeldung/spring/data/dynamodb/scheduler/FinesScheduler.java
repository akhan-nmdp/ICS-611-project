package com.baeldung.spring.data.dynamodb.scheduler;

import com.baeldung.spring.data.dynamodb.model.BorrowedBooks;
import com.baeldung.spring.data.dynamodb.model.Fines;
import com.baeldung.spring.data.dynamodb.service.BorrowedBooksService;
import com.baeldung.spring.data.dynamodb.service.FinesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class FinesScheduler {

    private static final Logger log = LoggerFactory.getLogger(FinesScheduler.class);
    @Autowired
    BorrowedBooksService borrowedBooksService;
    @Autowired
    FinesService finesService;

    @Scheduled(fixedRate = 10000)
    public void createFinesData() {
        log.info("start scheduled task- createFinesData");
    Iterable<BorrowedBooks> borrowedBooks = borrowedBooksService.findAll();
    borrowedBooks.forEach(borrowedBook -> {
        if (LocalDate.now().isAfter(LocalDate.parse(borrowedBook.getReturnDate()))) {
            Fines fineInDb = finesService.get(borrowedBook.getBorrowId());
            if (fineInDb != null) {
                log.info("fine already exists with fineId: {}", fineInDb.getFineId());
            } else {
                log.info("no fine present. create new fine for borrowId: {}, memberId: {}", borrowedBook.getBorrowId(), borrowedBook.getMemberId());
                finesService.create(borrowedBook.getBorrowId(), borrowedBook.getMemberId());
            }
        }
    });
        log.info("end scheduled task- createFinesData");
    }
}
