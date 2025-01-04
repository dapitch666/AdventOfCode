package org.anne.aoc2018;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day8Test {

    List<Integer> input = Arrays.asList(2, 3, 0, 3, 10, 11, 12, 1, 1, 0, 1, 99, 2, 1, 1, 2);

    @Test
    void testPart1() {
        assertEquals(138, Day8.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(66, Day8.part2(input));
    }
}
