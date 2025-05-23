package com.baeldung.boot.repository;

import com.baeldung.demo.DemoApplicationIntegrationTest;
import com.baeldung.demo.model.Foo;
import com.baeldung.demo.repository.FooRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@Transactional
public class HibernateSessionIntegrationTest extends DemoApplicationIntegrationTest {
    @Autowired
    private FooRepository fooRepository;

    @Test
    public void whenSavingWithCurrentSession_thenThrowNoException() {
        fooRepository.save(new Foo("Exception Solved"));

        Foo foo = fooRepository.findByName("Exception Solved");

        assertThat(foo, notNullValue());
        assertThat(foo.getId(), notNullValue());
        assertThat(foo.getName(), is("Exception Solved"));
    }
}
