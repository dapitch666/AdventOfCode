package org.anne.aoc2021;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day25Test {
    final List<String> input = List.of(
            "v...>>.vv>",
            ".vv>>.vv..",
            ">>.>v>...v",
            ">>v>>.>.v.",
            "v>v.vv.v..",
            ">.>>..v...",
            ".vv..>.>v.",
            "v.v..>>v.v",
            "....v..v.>"
    );

    @Test
    void testPart1() {
        assertEquals(58, Day25.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals("Merry Christmas!", Day25.part2());
    }
}