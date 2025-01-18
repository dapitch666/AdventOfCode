package org.anne.aoc2018;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day22Test {

    List<Integer> input = List.of(510, 10, 10);

    @Test
    void testPart1() {
        assertEquals(114, Day22.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(45, Day22.part2(input));
    }
}
