package org.anne.aoc2018;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day11Test {

    @Test
    void testPart1() {
        assertEquals("33,45", Day11.part1(18));
        assertEquals("21,61", Day11.part1(42));
    }

    @Test
    void testPart2() {
        assertEquals("90,269,16", Day11.part2(18));
        assertEquals("232,251,12", Day11.part2(42));
    }
}
