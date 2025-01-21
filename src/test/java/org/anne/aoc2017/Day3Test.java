package org.anne.aoc2017;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day3Test {

    @Test
    void testPart1() {
        assertEquals(0, Day3.part1(1));
        assertEquals(3, Day3.part1(12));
        assertEquals(2, Day3.part1(23));
        assertEquals(31, Day3.part1(1024));
    }

    @Test
    void testPart2() {
        assertEquals(2, Day3.part2(1));
        assertEquals(23, Day3.part2(12));
        assertEquals(806, Day3.part2(800));
    }
}
