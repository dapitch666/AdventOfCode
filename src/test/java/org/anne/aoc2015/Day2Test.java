package org.anne.aoc2015;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day2Test {

    List<String> input = Arrays.asList(
            "2x3x4",
            "1x1x10"
    );

    @Test
    void testPart1() {
        assertEquals(58 + 43, Day2.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(34 + 14, Day2.part2(input));
    }
}
