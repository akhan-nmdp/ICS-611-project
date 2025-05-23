package com.baeldung.inlining;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ConsecutiveNumbersSumUnitTest {

    private static final int TOTAL_NUMBERS = 10;

    @Test
    public void givenTotalIntegersNumber_whenSumCalculated_thenEquals() {
        ConsecutiveNumbersSum consecutiveNumbersSum = new ConsecutiveNumbersSum(TOTAL_NUMBERS);
        long expectedSum = calculateExpectedSum(TOTAL_NUMBERS);

        assertEquals(expectedSum, consecutiveNumbersSum.getTotalSum());
    }

    private long calculateExpectedSum(int totalNumbers) {
        return totalNumbers * (totalNumbers + 1) / 2;
    }
}
