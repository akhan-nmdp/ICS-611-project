package com.baeldung.javaxval.methodvalidation.constraints;

import java.time.LocalDate;

import com.baeldung.javaxval.methodvalidation.model.Reservation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidReservationValidator implements ConstraintValidator<ValidReservation, Reservation> {

    @Override
    public boolean isValid(Reservation reservation, ConstraintValidatorContext context) {

        if (reservation == null) {
            return true;
        }

        if (!(reservation instanceof Reservation)) {
            throw new IllegalArgumentException("Illegal method signature, expected parameter of type Reservation.");
        }

        if (reservation.getBegin() == null || reservation.getEnd() == null || reservation.getCustomer() == null) {
            return false;
        }

        return (reservation.getBegin()
            .isAfter(LocalDate.now())
            && reservation.getBegin()
                .isBefore(reservation.getEnd())
            && reservation.getRoom() > 0);
    }
}
