package org.anne.aoc2018;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day16Test {

    List<String> input = Arrays.asList(
            "Before: [3, 2, 1, 1]",
            "9 2 1 2",
            "After:  [3, 2, 2, 1]",
            ""
    );

    @Test
    void testPart1() {
        assertEquals(1, Day16.part1(input));
    }
}
