package com.baeldung.jpa.query.repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import com.baeldung.jpa.query.model.User;

public interface UserRepositoryCustom {

    List<User> findUserByEmails(Set<String> emails);

    List<User> findAllUsersByPredicates(Collection<Predicate<User>> predicates);

}
