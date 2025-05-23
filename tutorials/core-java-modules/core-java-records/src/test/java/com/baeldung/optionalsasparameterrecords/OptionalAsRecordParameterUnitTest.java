package com.baeldung.optionalsasparameterrecords;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class OptionalAsRecordParameterUnitTest {

    @Test
    public void givenRecordCreationWithOptional_thenCreateItProperly() {
        var emptyDescriptionProduct = new Product("television", 1699.99, Optional.empty());
        Assertions.assertEquals("television", emptyDescriptionProduct.name());
        Assertions.assertEquals(1699.99, emptyDescriptionProduct.price());
        Assertions.assertNull(emptyDescriptionProduct.description().orElse(null));
    }

    @Test
    public void whenRecordCreationWithNullOptional_thenReturnOptional() {
        User user = new User("john_doe", "john@example.com", null);
        Optional<String> optionalPhoneNumber = user.getOptionalPhoneNumber();

        Assertions.assertEquals(Optional.empty(), optionalPhoneNumber);
    }
}
