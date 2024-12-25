package org.anne.aoc2024;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day25Test {

    List<String> input = Arrays.asList(
            "#####",
            ".####",
            ".####",
            ".####",
            ".#.#.",
            ".#...",
            ".....",
            "",
            "#####",
            "##.##",
            ".#.##",
            "...##",
            "...#.",
            "...#.",
            ".....",
            "",
            ".....",
            "#....",
            "#....",
            "#...#",
            "#.#.#",
            "#.###",
            "#####",
            "",
            ".....",
            ".....",
            "#.#..",
            "###..",
            "###.#",
            "###.#",
            "#####",
            "",
            ".....",
            ".....",
            ".....",
            "#....",
            "#.#..",
            "#.#.#",
            "#####",
            ""
    );

    @Test
    void testPart1() {
        assertEquals(3, Day25.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals("Happy Tenth Anniversary, AdventOfCode!", Day25.part2());
    }
}
