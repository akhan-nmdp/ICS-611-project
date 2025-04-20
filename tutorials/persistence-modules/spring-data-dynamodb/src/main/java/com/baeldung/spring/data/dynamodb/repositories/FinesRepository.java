package com.baeldung.spring.data.dynamodb.repositories;

import com.baeldung.spring.data.dynamodb.model.Fines;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;


@EnableScan
public interface FinesRepository extends CrudRepository<Fines, String> {

    Fines findByBorrowId(String borrowId);
}
