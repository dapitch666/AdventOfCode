package org.anne.aoc2017;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day6Test {

    List<Integer> input = Arrays.asList(0, 2, 7, 0);

    @Test
    void testPart1() {
        assertEquals(5, Day6.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(4, Day6.part2(input));
    }
}
