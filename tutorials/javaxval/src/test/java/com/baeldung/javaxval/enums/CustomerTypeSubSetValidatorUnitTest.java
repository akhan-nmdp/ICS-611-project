package com.baeldung.javaxval.enums;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import org.junit.BeforeClass;
import org.junit.Test;

import com.baeldung.javaxval.enums.demo.Customer;
import com.baeldung.javaxval.enums.demo.CustomerType;
import com.baeldung.javaxval.enums.demo.CustomerUnitTest;

public class CustomerTypeSubSetValidatorUnitTest {

    private static Validator validator;

    @BeforeClass
    public static void setupValidatorInstance() {
        validator = Validation.buildDefaultValidatorFactory()
            .getValidator();
    }

    @Test
    public void whenEnumAnyOfSubset_thenShouldNotReportConstraintViolations() {
        Customer customer = new Customer.Builder().withCustomerTypeOfSubset(CustomerType.NEW)
            .build();
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertThat(violations.isEmpty()).isTrue();
    }

    @Test
    public void whenEnumNotAnyOfSubset_thenShouldGiveOccurrenceOfConstraintViolations() {
        Customer customer = new Customer.Builder().withCustomerTypeOfSubset(CustomerType.DEFAULT)
            .build();
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertThat(violations.size()).isEqualTo(1);

        assertThat(violations).anyMatch(CustomerUnitTest.havingPropertyPath("customerTypeOfSubset")
            .and(CustomerUnitTest.havingMessage("must be any of [NEW, OLD]")));
    }
}