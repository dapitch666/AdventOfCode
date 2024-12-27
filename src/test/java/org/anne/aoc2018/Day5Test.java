package org.anne.aoc2018;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day5Test {

    String input = "abBA";
    String input2 = "dabAcCaCBAcCcaDA";

    @Test
    void testPart1() {
        assertEquals(0, Day5.part1(input));
        assertEquals(10, Day5.part1(input2));
    }

    @Test
    void testPart2() {
        assertEquals(0, Day5.part2(input));
    }
}
