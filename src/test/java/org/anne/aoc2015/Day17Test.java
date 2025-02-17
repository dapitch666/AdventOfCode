package org.anne.aoc2015;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day17Test {

    List<Integer> input = List.of(20, 15, 10, 5, 5);

    @Test
    void testPart1() {
        assertEquals(4, Day17.part1(input, 25));
    }

    @Test
    void testPart2() {
        assertEquals(3, Day17.part2(input, 25));
    }
}
