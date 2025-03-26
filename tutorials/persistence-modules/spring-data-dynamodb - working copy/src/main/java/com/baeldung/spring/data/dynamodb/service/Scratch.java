package com.baeldung.spring.data.dynamodb.service;

import java.time.LocalDate;

public class Scratch {

    public static void main(String args[]) {
        LocalDate date1 = LocalDate.parse("2025-03-22");
        LocalDate date2 = LocalDate.parse("2025-03-23");
        System.out.println("Not isEqual for date in past" + date2.isAfter(date1));

//        System.out.println("Not isEqual for date in past" + !returnDate1.isEqual(LocalDate.now()));
//        System.out.println("Not isAfter for date in past" + returnDate1.isAfter(LocalDate.now()));
//        LocalDate returnDate2 = LocalDate.parse("2025-03-23");
//        System.out.println("Not isEqual for current date" + !returnDate2.isEqual(LocalDate.now()));
//        System.out.println("Not isAfter for current date" + returnDate2.isAfter(LocalDate.now()));
//        LocalDate returnDate3 = LocalDate.parse("2025-03-25");
//        System.out.println("Not isEqual for date in future" + !returnDate3.isEqual(LocalDate.now()));
//        System.out.println("Not isAfter for date in future" + returnDate3.isAfter(LocalDate.now()));
    }
}
