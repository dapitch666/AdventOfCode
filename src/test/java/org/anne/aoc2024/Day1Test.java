package org.anne.aoc2024;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day1Test {

    List<String> input = Arrays.asList(
            "3   4",
            "4   3",
            "2   5",
            "1   3",
            "3   9",
            "3   3"
    );

    @Test
    void testPart1() {
        assertEquals(11, Day1.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(31, Day1.part2(input));
    }
}
