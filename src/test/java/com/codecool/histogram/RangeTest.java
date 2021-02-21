package com.codecool.histogram;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RangeTest {

    String sample = "lengthOfThisWordIs20";
    private static Range range;

    @Test
    public void argumentIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Range(-1, 5));
    }

    @Test
    public void firstArgumentLargerThanSecond() {
        assertThrows(IllegalArgumentException.class, () -> new Range(5, 2));
    }

    @Test
    public void lengthOfWordIsInRange() {
        range = new Range(19, 21);
        assertTrue(range.isInRange(sample));
    }

    @Test
    public void lengthOfWordEqualsToRange() {
        range = new Range(20, 20);
        assertTrue(range.isInRange(sample));
    }

    @Test
    public void lengthOfWordEqualsToRangeFrom() {
        range = new Range(20, 21);
        assertTrue(range.isInRange(sample));
    }

    @Test
    public void lengthOfWordEqualsToRangeTo() {
        range = new Range(19, 20);
        assertTrue(range.isInRange(sample));
    }

    @Test
    public void toStringHasSpecificFormat() {
        range = new Range(19, 21);
        assertTrue(range.toString().matches("\\d{2} - \\d{2}"));
    }
    
}
