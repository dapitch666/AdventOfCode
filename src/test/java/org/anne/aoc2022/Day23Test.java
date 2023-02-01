package org.anne.aoc2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day23Test {
    List<String> input = Arrays.asList(
            "....#..",
            "..###.#",
            "#...#.#",
            ".#...##",
            "#.###..",
            "##.#.##",
            ".#..#.."
    );

    @Test
    void part1() {
        assertEquals(110, Day23.part1(input));
    }

    @Test
    void part2() {
        assertEquals(20, Day23.part2(input));
    }
}