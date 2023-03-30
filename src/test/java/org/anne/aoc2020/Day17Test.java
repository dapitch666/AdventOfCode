package org.anne.aoc2020;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day17Test {

    final List<String> input = List.of(
            ".#.",
            "..#",
            "###"
    );
    @Test
    void testPart1() {
        assertEquals(112, Day17.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(848, Day17.part2(input));
    }
}