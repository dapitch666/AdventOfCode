package org.anne.aoc2016;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day20Test {

    List<String> input = Arrays.asList(
            "5-8",
            "0-2",
            "4-7"
    );

    @Test
    void testPart1() {
        assertEquals(3, Day20.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(2, Day20.part2(input, 9));
    }
}
