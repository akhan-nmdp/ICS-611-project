package com.baeldung.spring.data.dynamodb.service.impl;

import com.baeldung.spring.data.dynamodb.model.Books;
import com.baeldung.spring.data.dynamodb.model.BorrowedBooks;
import com.baeldung.spring.data.dynamodb.model.Members;
import com.baeldung.spring.data.dynamodb.repositories.BooksRepository;
import com.baeldung.spring.data.dynamodb.repositories.BorrowedBooksRepository;
import com.baeldung.spring.data.dynamodb.repositories.MembersRepository;
import com.baeldung.spring.data.dynamodb.request.dto.BookReservation;
import com.baeldung.spring.data.dynamodb.response.dto.BorrowedBooksDto;
import com.baeldung.spring.data.dynamodb.service.BorrowedBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class BorrowedBooksServiceImpl implements BorrowedBooksService {

    @Autowired
    BorrowedBooksRepository borrowedBooksRepository;
    @Autowired
    MembersRepository membersRepository;
    @Autowired
    BooksRepository booksRepository;


    @Override
    public List<BorrowedBooksDto> getAll() {

        Iterable<BorrowedBooks> borrowedBooks = borrowedBooksRepository.findAll();
        List<BorrowedBooksDto> borrowedBooksDtoList = new ArrayList<>();
        for (BorrowedBooks borrowedBook : borrowedBooks) {
            BorrowedBooksDto borrowedBooksDto = new BorrowedBooksDto();
            borrowedBooksDto.setBorrowId(borrowedBook.getBorrowId());
            booksRepository.findById(borrowedBook.getBookId()).ifPresent(book -> borrowedBooksDto.setBookName(book.getTitle()));
            membersRepository.findById(borrowedBook.getMemberId()).ifPresent(member -> borrowedBooksDto.setMemberName(member.getName()));
            borrowedBooksDto.setBorrowDate(borrowedBook.getBorrowDate());
            borrowedBooksDto.setReturnDate(borrowedBook.getReturnDate());
            borrowedBooksDtoList.add(borrowedBooksDto);
        }
        return  borrowedBooksDtoList;
    }

    @Override
    public Iterable<BorrowedBooks> findAll() {
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
        Members memberInDb = membersRepository.findByName(bookReservation.getMemberName());
        borrowedBooks.setMemberId(memberInDb.getMemberId());
        borrowedBooks.setBorrowDate(bookReservation.getBorrowDate());
        borrowedBooks.setReturnDate(LocalDate.now().plusWeeks(1).toString());
        Books bookInDb = booksRepository.findById(bookReservation.getBookId()).get();
        bookInDb.setAvailability(false);
        booksRepository.save(bookInDb);
        borrowedBooksRepository.save(borrowedBooks);
    }
}
