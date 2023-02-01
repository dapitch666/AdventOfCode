package org.anne.aoc2021;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day7Test {
    final List<Integer> input = List.of(16,1,2,0,4,2,7,1,2,14);

    @Test
    void testPart1() {
        assertEquals(37, Day7.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(168, Day7.part2(input));
    }
}