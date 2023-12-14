package org.anne.aoc2023;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day14Test {

    List<String> input = Arrays.asList(
            "O....#....",
            "O.OO#....#",
            ".....##...",
            "OO.#O....O",
            ".O.....O#.",
            "O.#..O.#.#",
            "..O..#O..O",
            ".......O..",
            "#....###..",
            "#OO..#...."
    );
    
    @Test
    void testPart1() {
        assertEquals(136, Day14.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(64, Day14.part2(input));
    }
 }
