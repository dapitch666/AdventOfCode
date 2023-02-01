package org.anne.aoc2020;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10Test {

    List<Integer> input = List.of(28, 33, 18, 42, 31, 14, 46, 20, 48, 47, 24, 23, 49, 45, 19, 38, 39, 11, 1,
                    32, 25, 35, 8, 17, 7, 9, 4, 2, 34, 10, 3);
    @Test
    void testPart1() {
        assertEquals(220, Day10.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(19208, Day10.part2(input));
    }
}