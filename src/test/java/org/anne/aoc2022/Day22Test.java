package org.anne.aoc2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day22Test {
    List<String> input = Arrays.asList(
            "        ...#",
            "        .#..",
            "        #...",
            "        ....",
            "...#.......#",
            "........#...",
            "..#....#....",
            "..........#.",
            "        ...#....",
            "        .....#..",
            "        .#......",
            "        ......#.",
            "",
            "10R5L5R10L4R5L5"
    );

    @Test
    void part1() {
        assertEquals(6032, Day22.part1(input));
    }

    @Test
    void part2() {
        // No test for part2 as the fold is not at the same place as the actual input...
    }
}