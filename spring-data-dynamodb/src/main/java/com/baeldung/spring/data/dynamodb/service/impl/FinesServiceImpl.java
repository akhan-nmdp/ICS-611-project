package com.baeldung.spring.data.dynamodb.service.impl;

import com.baeldung.spring.data.dynamodb.model.Books;
import com.baeldung.spring.data.dynamodb.model.BorrowedBooks;
import com.baeldung.spring.data.dynamodb.model.Fines;
import com.baeldung.spring.data.dynamodb.model.Members;
import com.baeldung.spring.data.dynamodb.repositories.BooksRepository;
import com.baeldung.spring.data.dynamodb.repositories.BorrowedBooksRepository;
import com.baeldung.spring.data.dynamodb.repositories.FinesRepository;
import com.baeldung.spring.data.dynamodb.repositories.MembersRepository;
import com.baeldung.spring.data.dynamodb.response.dto.FinesDto;
import com.baeldung.spring.data.dynamodb.service.FinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class FinesServiceImpl implements FinesService {

    @Autowired
    FinesRepository finesRepository;
    @Autowired
    BorrowedBooksRepository borrowedBooksRepository;
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
       fine.setIssueDate(LocalDate.now().toString());
       finesRepository.save(fine);
    }

    @Override
    public List<FinesDto> getAll() {
        Iterable<Fines> fines = finesRepository.findAll();
        List<FinesDto> finesDtoList = new ArrayList<>();
        for (Fines fine : fines) {
            FinesDto finesDto = new FinesDto();
            Optional<BorrowedBooks> borrowedBook = borrowedBooksRepository.findById(fine.getBorrowId());
            Optional<Books> book = booksRepository.findById(borrowedBook.get().getBookId());
            finesDto.setBookName(book.get().getTitle());
            Optional<Members> member = membersRepository.findById(fine.getMemberId());
            finesDto.setMemberName(member.get().getName());
            finesDto.setFineAmount(fine.getFineAmount());
            finesDto.setIssueDate(fine.getIssueDate());
            finesDto.setPaid(finesDto.isPaid());
            finesDtoList.add(finesDto);
        }
        return finesDtoList;
    }

    @Override
    public Fines get(String borrowId) {
        return finesRepository.findByBorrowId(borrowId);
    }
}
