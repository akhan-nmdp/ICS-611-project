package com.baeldung.algorithms.rectanglesoverlap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RectangleUnitTest {

    @Test
    public void givenTwoOverlappingRectangles_whenisOverlappingCalled_shouldReturnTrue() {
        Rectangle rectangle1 = new Rectangle(new Point(2, 1), new Point(4, 3));
        Rectangle rectangle2 = new Rectangle(new Point(1, 1), new Point(6, 4));
        assertTrue(rectangle1.isOverlapping(rectangle2));

        rectangle1 = new Rectangle(new Point(-5, -2), new Point(2, 3));
        rectangle2 = new Rectangle(new Point(-2, -1), new Point(5, 2));
        assertTrue(rectangle1.isOverlapping(rectangle2));

        rectangle1 = new Rectangle(new Point(-5, 1), new Point(2, 4));
        rectangle2 = new Rectangle(new Point(-2, -2), new Point(5, 5));
        assertTrue(rectangle1.isOverlapping(rectangle2));
    }

    @Test
    public void givenTwoNonOverlappingRectangles_whenisOverlappingCalled_shouldReturnFalse() {
        Rectangle rectangle1 = new Rectangle(new Point(-5, 1), new Point(-3, 4));
        Rectangle rectangle2 = new Rectangle(new Point(-2, -2), new Point(5, 5));
        assertFalse(rectangle1.isOverlapping(rectangle2));

        rectangle1 = new Rectangle(new Point(-5, 1), new Point(3, 4));
        rectangle2 = new Rectangle(new Point(-2, -2), new Point(5, -1));
        assertFalse(rectangle1.isOverlapping(rectangle2));

        rectangle1 = new Rectangle(new Point(-2, 1), new Point(0, 3));
        rectangle2 = new Rectangle(new Point(3, 1), new Point(5, 4));
        assertFalse(rectangle1.isOverlapping(rectangle2));
    }

    @Test
    public void givenAdjacentRectangles_whensOverlappingCalled_shouldReturnTrue() {
Rectangle rectangle1 = new Rectangle(new Point(0, 0), new Point(5, 14));
Rectangle rectangle2 = new Rectangle(new Point(5, 0), new Point(17, 14));
assertTrue(rectangle1.isOverlapping(rectangle2));
    }

    @Test
    public void givenAdjacentRectangles_whensOverlappingWithoutBordersCalled_shouldReturnFalse() {
        Rectangle rectangle1 = new Rectangle(new Point(0, 0), new Point(5, 14));
        Rectangle rectangle2 = new Rectangle(new Point(5, 0), new Point(17, 14));
        assertFalse(rectangle1.isOverlappingWithoutBorders(rectangle2));
    }

}
