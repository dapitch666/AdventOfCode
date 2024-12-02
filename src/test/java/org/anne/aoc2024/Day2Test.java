package org.anne.aoc2024;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day2Test {

    List<String> input = Arrays.asList(
            "7 6 4 2 1",
            "1 2 7 8 9",
            "9 7 6 2 1",
            "1 3 2 4 5",
            "8 6 4 4 1",
            "1 3 6 7 9"
    );

    @Test
    void testPart1() {
        assertEquals(2, Day2.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(4, Day2.part2(input));
    }
}
