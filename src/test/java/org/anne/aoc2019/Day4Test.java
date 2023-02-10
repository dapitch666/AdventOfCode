package org.anne.aoc2019;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day4Test {

    @Test
    void testPart1() {
        assertTrue(Day4.hasIdenticalAdjacentDigits("122345"));
        assertTrue(Day4.hasIdenticalAdjacentDigits("111111"));
        assertFalse(Day4.hasIdenticalAdjacentDigits("123789"));
        assertTrue(Day4.isOrdered("111123"));
        assertTrue(Day4.isOrdered("135679"));
        assertFalse(Day4.isOrdered("223450"));
    }

    @Test
    void testPart2() {
        assertTrue(Day4.hasTwoIdenticalAdjacentDigits("112233"));
        assertTrue(Day4.hasTwoIdenticalAdjacentDigits("111122"));
        assertFalse(Day4.hasTwoIdenticalAdjacentDigits("123444"));
    }
}