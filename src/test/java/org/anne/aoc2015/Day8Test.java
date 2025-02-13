package org.anne.aoc2015;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day8Test {

    List<String> input = Arrays.asList(
            "\"\"",
            "\"abc\"",
            "\"aaa\\\"aaa\"",
            "\"\\x27\""
    );

    @Test
    void testPart1() {
        assertEquals(12, Day8.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(19, Day8.part2(input));
    }
}
