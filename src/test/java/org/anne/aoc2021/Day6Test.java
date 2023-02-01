package org.anne.aoc2021;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day6Test {
    final List<Integer> input = List.of(3, 4, 3, 1, 2);

    @Test
    void testPart1() {
        assertEquals(5934L, Day6.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(26984457539L, Day6.part2(input));
    }
}