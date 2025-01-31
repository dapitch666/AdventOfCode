package org.anne.aoc2016;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day1Test {

    @Test
    void testPart1() {
        assertEquals(5, Day1.part1("R2, L3"));
        assertEquals(2, Day1.part1("R2, R2, R2"));
        assertEquals(12, Day1.part1("R5, L5, R5, R3"));
    }

    @Test
    void testPart2() {
        assertEquals(4, Day1.part2("R8, R4, R4, R8"));
    }
}
