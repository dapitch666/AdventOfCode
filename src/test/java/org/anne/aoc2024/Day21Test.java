package org.anne.aoc2024;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day21Test {

    List<String> input = Arrays.asList(
            "029A",
            "980A",
            "179A",
            "456A",
            "379A"
    );

    @Test
    void testPart1() {
        assertEquals(126384, Day21.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(154115708116294L, Day21.part2(input));
    }
}
