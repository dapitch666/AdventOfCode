package org.anne.aoc2024;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day7Test {

    List<String> input = Arrays.asList(
            "190: 10 19",
            "3267: 81 40 27",
            "83: 17 5",
            "156: 15 6",
            "7290: 6 8 6 15",
            "161011: 16 10 13",
            "192: 17 8 14",
            "21037: 9 7 18 13",
            "292: 11 6 16 20"
    );

    @Test
    void testPart1() {
        assertEquals(3749, Day7.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(11387, Day7.part2(input));
    }
}
