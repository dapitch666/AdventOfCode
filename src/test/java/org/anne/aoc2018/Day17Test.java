package org.anne.aoc2018;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day17Test {

    List<String> input = Arrays.asList(
            "x=495, y=2..7",
            "y=7, x=495..501",
            "x=501, y=3..7",
            "x=498, y=2..4",
            "x=506, y=1..2",
            "x=498, y=10..13",
            "x=504, y=10..13",
            "y=13, x=498..504"
    );

    @Test
    void testPart1() {
        assertEquals(57, Day17.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(29, Day17.part2(input));
    }
}
