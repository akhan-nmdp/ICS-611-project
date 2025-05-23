package com.baeldung.algorithms.distancebetweenpoints;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DistanceBetweenPointsServiceUnitTest {

    private DistanceBetweenPointsService service = new DistanceBetweenPointsService();

    @Test
    public void givenTwoPoints_whenCalculateDistanceByFormula_thenCorrect() {

        double x1 = 3;
        double y1 = 4;
        double x2 = 7;
        double y2 = 1;

        double distance = service.calculateDistanceBetweenPoints(x1, y1, x2, y2);

        assertEquals(5, distance, 0.001);

    }

    @Test
    public void givenTwoPoints_whenCalculateDistanceWithHypot_thenCorrect() {

        double x1 = 3;
        double y1 = 4;
        double x2 = 7;
        double y2 = 1;

        double distance = service.calculateDistanceBetweenPointsWithHypot(x1, y1, x2, y2);

        assertEquals(5, distance, 0.001);

    }

    @Test
    public void givenTwoPoints_whenCalculateDistanceWithPoint2D_thenCorrect() {

        double x1 = 3;
        double y1 = 4;
        double x2 = 7;
        double y2 = 1;

        double distance = service.calculateDistanceBetweenPointsWithPoint2D(x1, y1, x2, y2);

        assertEquals(5, distance, 0.001);

    }
}
