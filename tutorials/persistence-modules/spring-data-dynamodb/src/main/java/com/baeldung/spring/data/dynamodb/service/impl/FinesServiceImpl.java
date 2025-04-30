package com.baeldung.spring.data.dynamodb.service.impl;

import com.baeldung.spring.data.dynamodb.model.BorrowedBooks;
import com.baeldung.spring.data.dynamodb.model.Fines;
import com.baeldung.spring.data.dynamodb.repositories.BooksRepository;
import com.baeldung.spring.data.dynamodb.repositories.FinesRepository;
import com.baeldung.spring.data.dynamodb.repositories.MembersRepository;
import com.baeldung.spring.data.dynamodb.response.dto.FinesDto;
import com.baeldung.spring.data.dynamodb.service.BorrowedBooksService;
import com.baeldung.spring.data.dynamodb.service.FinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class FinesServiceImpl implements FinesService {

    @Autowired
    FinesRepository finesRepository;
    @Autowired
    BorrowedBooksService borrowedBooksService;
    @Autowired
    MembersRepository membersRepository;
    @Autowired
    BooksRepository booksRepository;

    @Override
    public void create(String borrowId, String memberId) {
       Fines fine = new Fines();
       fine.setMemberId(memberId);
       fine.setBorrowId(borrowId);
       fine.setFineAmount(1);
       fine.setPaid(false);
       fine.setIssueDate(LocalDate.now().toString());
       BorrowedBooks borrowedBook = borrowedBooksService.get(fine.getBorrowId());
       borrowedBook.setBorrowStatus(BorrowedBooks.BorrowStatus.LATE.name());
       borrowedBooksService.save(borrowedBook);
       finesRepository.save(fine);
    }

    @Override
    public List<FinesDto> getAll() {
        List<FinesDto> finesDtoList = new ArrayList<>();
        Iterable<Fines> finesInDb = finesRepository.findAll();
        for (Fines fine: finesInDb) {
            FinesDto finesDto = new FinesDto();
            finesDto.setFineId(fine.getFineId());
            finesDto.setPaid(fine.getPaid());
            finesDto.setIssueDate(fine.getIssueDate());
            finesDto.setFineAmount(fine.getFineAmount());
            BorrowedBooks borrowedBook = borrowedBooksService.get(fine.getBorrowId());
            booksRepository.findById(borrowedBook.getBookId()).ifPresent(book -> finesDto.setBookName(book.getTitle()));
            membersRepository.findById(borrowedBook.getMemberId()).ifPresent(member -> finesDto.setMemberName(member.getName()));
            finesDtoList.add(finesDto);
        }
        return finesDtoList;
    }

    @Override
    public Fines get(String borrowId) {
        return finesRepository.findByBorrowId(borrowId);
    }
}
