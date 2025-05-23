package com.baeldung.algorithms.maximumsubarray;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class KadaneAlgorithmUnitTest {

    @Test
    void givenArrayWithNegativeNumberWhenMaximumSubarrayThenReturnsExpectedResult() {
        //given
        int[] arr = new int[] { -3, 1, -8, 4, -1, 2, 1, -5, 5 };
        //when
        KadaneAlgorithm algorithm = new KadaneAlgorithm();
        int maxSum = algorithm.maxSubArraySum(arr);
        //then
        assertEquals(6, maxSum);
    }

    @Test
    void givenArrayWithAllNegativeNumbersWhenMaximumSubarrayThenReturnsExpectedResult() {
        //given
        int[] arr = new int[] { -8, -7, -5, -4, -3, -1, -2 };
        //when
        KadaneAlgorithm algorithm = new KadaneAlgorithm();
        int maxSum = algorithm.maxSubArraySum(arr);
        //then
        assertEquals(-1, maxSum);
    }

    @Test
    void givenArrayWithAllPosiitveNumbersWhenMaximumSubarrayThenReturnsExpectedResult() {
        //given
        int[] arr = new int[] {4, 1, 3, 2};
        //when
        KadaneAlgorithm algorithm = new KadaneAlgorithm();
        int maxSum = algorithm.maxSubArraySum(arr);
        //then
        assertEquals(10, maxSum);
    }

    @Test
    void givenArrayToTestStartIndexWhenMaximumSubarrayThenReturnsExpectedResult() {
        //given
        int[] arr = new int[] { 1, 2, -1, 3, -6, -2 };
        //when
        KadaneAlgorithm algorithm = new KadaneAlgorithm();
        int maxSum = algorithm.maxSubArraySum(arr);
        //then
        assertEquals(5, maxSum);
    }

}