package org.anne.aoc2016;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day21Test {

    List<String> input = Arrays.asList(
            "swap position 4 with position 0",
            "swap letter d with letter b",
            "reverse positions 0 through 4",
            "rotate left 1 step",
            "move position 1 to position 4",
            "move position 3 to position 0",
            "rotate based on position of letter b",
            "rotate based on position of letter d"
    );

    @Test
    void testPart1() {
        assertEquals("decab", Day21.part1(input, "abcde"));
    }

    @Test
    void testPart2() {
        assertEquals("abcde", Day21.part2(input, "decab"));
    }
}
