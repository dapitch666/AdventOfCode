package org.anne.aoc2015;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day24Test {

    List<Integer> input = Arrays.asList(1, 2, 3, 4, 5, 7, 8, 9, 10, 11);

    @Test
    void testPart1() {
        assertEquals(99, Day24.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(44, Day24.part2(input));
    }
}
