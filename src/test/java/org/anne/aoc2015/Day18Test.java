package org.anne.aoc2015;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day18Test {

    List<String> input = Arrays.asList(
            ".#.#.#",
            "...##.",
            "#....#",
            "..#...",
            "#.#..#",
            "####.."
    );

    @Test
    void testPart1() {
        assertEquals(4, Day18.part1(input, 4));
    }

    @Test
    void testPart2() {
        assertEquals(17, Day18.part2(input, 5));
    }
}
