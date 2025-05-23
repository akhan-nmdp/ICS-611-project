package com.baeldung.algorithms.maximumsubarray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KadaneAlgorithm {

    private Logger logger = LoggerFactory.getLogger(KadaneAlgorithm.class.getName());

    public int maxSubArraySum(int[] arr) {

        int size = arr.length;
        int start = 0;
        int end = 0;

        int maxSoFar = arr[0], maxEndingHere = arr[0];

        for (int i = 1; i < size; i++) {
            maxEndingHere = maxEndingHere + arr[i];
            if (arr[i] > maxEndingHere) {
                maxEndingHere = arr[i];
                if (maxSoFar < maxEndingHere) {
                    start = i;
                }
            }
            if (maxSoFar < maxEndingHere) {
                maxSoFar = maxEndingHere;
                end = i;
            }
        }
        logger.info("Found Maximum Subarray between {} and {}", Math.min(start, end), end);
        return maxSoFar;
    }
}
