package org.anne.aoc2017;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day5Test {

    List<Integer> input = Arrays.asList(0, 3, 0, 1, -3);

    @Test
    void testPart1() {
        assertEquals(5, Day5.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(10, Day5.part2(input));
    }
}
