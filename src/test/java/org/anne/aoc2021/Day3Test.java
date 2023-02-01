package org.anne.aoc2021;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day3Test {
    final List<String> input = List.of(
            "00100",
            "11110",
            "10110",
            "10111",
            "10101",
            "01111",
            "00111",
            "11100",
            "10000",
            "11001",
            "00010",
            "01010"
    );

    @Test
    void testPart1() {
        assertEquals(198L, Day3.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(230L, Day3.part2(input));
    }

    @Test
    void testTranspose() {
        List<String> expected = Arrays.asList("147", "258", "369");
        assertEquals(expected, Day3.transpose(Arrays.asList("123", "456", "789")));
    }
}
