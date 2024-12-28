package org.anne.aoc2018;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day6Test {

    List<String> input = Arrays.asList(
            "1, 1",
            "1, 6",
            "8, 3",
            "3, 4",
            "5, 5",
            "8, 9"
    );

    @Test
    void testPart1() {
        assertEquals(17, Day6.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(16, Day6.part2(input, 32));
    }
}
