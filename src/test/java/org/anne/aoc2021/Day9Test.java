package org.anne.aoc2021;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day9Test {
    final List<String> input = List.of("2199943210", "3987894921", "9856789892", "8767896789", "9899965678");

    @Test
    void testPart1() {
        assertEquals(15, Day9.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(1134L, Day9.part2(input));
    }
}