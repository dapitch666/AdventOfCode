package org.anne.aoc2024;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day6Test {

    List<String> input = Arrays.asList(
            "....#.....",
            ".........#",
            "..........",
            "..#.......",
            ".......#..",
            "..........",
            ".#..^.....",
            "........#.",
            "#.........",
            "......#..."
    );

    @Test
    void testPart1() {
        assertEquals(41, Day6.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(6, Day6.part2(input));
    }
}
