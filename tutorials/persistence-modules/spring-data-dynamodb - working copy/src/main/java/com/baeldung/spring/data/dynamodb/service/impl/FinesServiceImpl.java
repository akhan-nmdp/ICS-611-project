package com.baeldung.spring.data.dynamodb.service.impl;

import com.baeldung.spring.data.dynamodb.model.Fines;
import com.baeldung.spring.data.dynamodb.repositories.FinesRepository;
import com.baeldung.spring.data.dynamodb.service.FinesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Transactional
@Service
public class FinesServiceImpl implements FinesService {

    @Autowired
    FinesRepository finesRepository;

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
    public Iterable<Fines> getAll() {
        return finesRepository.findAll();
    }

    @Override
    public Fines get(String borrowId) {
        return finesRepository.findByBorrowId(borrowId);
    }
}
