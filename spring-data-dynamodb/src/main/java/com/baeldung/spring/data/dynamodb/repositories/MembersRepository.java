package com.baeldung.spring.data.dynamodb.repositories;

import com.baeldung.spring.data.dynamodb.model.Books;
import com.baeldung.spring.data.dynamodb.model.Members;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface MembersRepository extends CrudRepository<Members, String> {
}
