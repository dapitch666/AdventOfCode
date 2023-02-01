package org.anne.aoc2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Test {
    List<String> input = Arrays.asList(
            "Sabqponm",
            "abcryxxl",
            "accszExk",
            "acctuvwj",
            "abdefghi"
    );

    @Test
    void part1() {
        assertEquals(31, Day12.part1(input));
    }

    @Test
    void part2() {
        assertEquals(29, Day12.part2(input));
    }
}