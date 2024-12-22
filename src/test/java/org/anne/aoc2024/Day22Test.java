package org.anne.aoc2024;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day22Test {

    List<Integer> input = Arrays.asList(1, 10, 100, 2024);
    List<Integer> input2 = Arrays.asList(1, 2, 3, 2024);

    @Test
    void testPart1() {
        assertEquals(37327623, Day22.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(23, Day22.part2(input2));
    }
}
