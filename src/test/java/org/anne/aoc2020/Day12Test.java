package org.anne.aoc2020;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day12Test {

    List<String> input = List.of("F10", "N3", "F7", "R90", "F11");

    @Test
    void testPart1() {
        assertEquals(25, Day12.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(286, Day12.part2(input));
    }
}