package org.anne.aoc2020;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day3Test {
    final List<String> input = List.of(
            "..##.......",
            "#...#...#..",
            ".#....#..#.",
            "..#.#...#.#",
            ".#...##..#.",
            "..#.##.....",
            ".#.#.#....#",
            ".#........#",
            "#.##...#...",
            "#...##....#",
            ".#..#...#.#"
    );

    @Test
    void testPart1() {
        assertEquals(7, Day3.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(336, Day3.part2(input));
    }
}
