package org.anne.aoc2015;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day3Test {

    @Test
    void testPart1() {
        assertEquals(2, Day3.part1(">"));
        assertEquals(4, Day3.part1("^>v<"));
        assertEquals(2, Day3.part1("^v^v^v^v^v"));
    }

    @Test
    void testPart2() {
        assertEquals(3, Day3.part2(">v"));
        assertEquals(3, Day3.part2("^>v<"));
        assertEquals(11, Day3.part2("^v^v^v^v^v"));
    }
}
