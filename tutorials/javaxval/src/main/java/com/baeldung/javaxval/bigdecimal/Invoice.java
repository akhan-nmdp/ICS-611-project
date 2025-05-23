package com.baeldung.javaxval.bigdecimal;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;

public class Invoice {

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 3, fraction = 2)
    private BigDecimal price;
    private String description;

    public Invoice(BigDecimal price, String description) {
        this.price = price;
        this.description = description;
    }
}
