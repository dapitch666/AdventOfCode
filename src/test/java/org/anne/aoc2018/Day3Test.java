package org.anne.aoc2018;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day3Test {

    List<String> input = Arrays.asList(
            "#1 @ 1,3: 4x4",
            "#2 @ 3,1: 4x4",
            "#3 @ 5,5: 2x2"
    );

    @Test
    void testPart1() {
        assertEquals(4, Day3.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(3, Day3.part2(input));
    }
}
