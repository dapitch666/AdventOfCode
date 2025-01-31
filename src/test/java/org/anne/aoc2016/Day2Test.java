package org.anne.aoc2016;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day2Test {

    List<String> input = Arrays.asList(
            "ULL",
            "RRDDD",
            "LURDL",
            "UUUUD"
    );

    @Test
    void testPart1() {
        assertEquals("1985", Day2.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals("5DB3", Day2.part2(input));
    }
}
