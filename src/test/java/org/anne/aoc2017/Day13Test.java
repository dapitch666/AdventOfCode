package org.anne.aoc2017;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day13Test {

    List<String> input = Arrays.asList(
            "0: 3",
            "1: 2",
            "4: 4",
            "6: 4"
    );

    @Test
    void testPart1() {
        assertEquals(24, Day13.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(10, Day13.part2(input));
    }
}
