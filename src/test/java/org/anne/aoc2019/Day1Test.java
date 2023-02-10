package org.anne.aoc2019;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day1Test {

    @Test
    void testPart1() {
        assertEquals(2, Day1.getFuel(12));
        assertEquals(2, Day1.getFuel(14));
        assertEquals(654, Day1.getFuel(1969));
        assertEquals(33583, Day1.getFuel(100756));
    }

    @Test
    void testPart2() {
        assertEquals(2, Day1.fuelFuel(14));
        assertEquals(966, Day1.fuelFuel(1969));
        assertEquals(50346, Day1.fuelFuel(100756));
    }
}