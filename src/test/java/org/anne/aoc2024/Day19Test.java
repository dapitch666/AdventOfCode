package org.anne.aoc2024;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day19Test {

    List<String> input = Arrays.asList(
            "r, wr, b, g, bwu, rb, gb, br",
            "",
            "brwrr",
            "bggr",
            "gbbr",
            "rrbgbr",
            "ubwu",
            "bwurrg",
            "brgr",
            "bbrgwb"
    );

    @Test
    void testPart1() {
        assertEquals(6, Day19.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(16, Day19.part2(input));
    }
}
