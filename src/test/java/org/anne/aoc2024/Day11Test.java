package org.anne.aoc2024;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day11Test {

    String input = "125 17";

    @Test
    void testPart1() {
        assertEquals(55312, Day11.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(65601038650482L, Day11.part2(input));
    }
}
