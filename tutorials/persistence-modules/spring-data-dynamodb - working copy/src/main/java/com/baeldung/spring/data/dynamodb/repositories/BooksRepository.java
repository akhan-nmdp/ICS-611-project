package com.baeldung.spring.data.dynamodb.repositories;

import com.baeldung.spring.data.dynamodb.model.Books;
import com.baeldung.spring.data.dynamodb.model.ProductInfo;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface BooksRepository extends CrudRepository<Books, String> {
    Optional<Books> findByIsbn(String isbn);
}
