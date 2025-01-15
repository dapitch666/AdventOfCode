package org.anne.aoc2018;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day18Test {

    List<String> input = Arrays.asList(
            ".#.#...|#.",
            ".....#|##|",
            ".|..|...#.",
            "..|#.....#",
            "#.#|||#|#|",
            "...#.||...",
            ".|....|...",
            "||...#|.#|",
            "|.||||..|.",
            "...#.|..|."
    );

    @Test
    void testPart1() {
        assertEquals(1147, Day18.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(0, Day18.part2(input));
    }
}
