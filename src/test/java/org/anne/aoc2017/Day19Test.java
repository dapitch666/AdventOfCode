package org.anne.aoc2017;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day19Test {

    List<String> input = Arrays.asList(
            "     |          ",
            "     |  +--+    ",
            "     A  |  C    ",
            " F---|----E|--+ ",
            "     |  |  |  D ",
            "     +B-+  +--+ "
    );

    @Test
    void testPart1() {
        assertEquals("ABCDEF", Day19.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(38, Day19.part2(input));
    }
}
