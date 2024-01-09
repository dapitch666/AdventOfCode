package org.anne.aoc2023;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day11Test {

    List<String> input = Arrays.asList(
         "...#......",
         ".......#..",
         "#.........",
         "..........",
         "......#...",
         ".#........",
         ".........#",
         "..........",
         ".......#..",
         "#...#....."
    );
    
    @Test
    void testPart1() {
        assertEquals(374, Day11.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(1030, Day11.part2(input, 10));
    }
 }
