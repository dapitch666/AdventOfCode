package org.anne.aoc2023;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day21Test {

    List<String> input = Arrays.asList(
            "...........",
            ".....###.#.",
            ".###.##..#.",
            "..#.#...#..",
            "....#.#....",
            ".##..S####.",
            ".##..#...#.",
            ".......##..",
            ".##.#.####.",
            ".##..##.##.",
            "..........."
    );
    
    @Test
    void testPart1() {
        assertEquals(16, Day21.part1(input, 6));
    }

    @Test
    void testPart2() {
        // assertEquals(50, Day21.part2(input, 10));
    }
}
