package org.anne.aoc2024;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Test {

    List<String> input1 = Arrays.asList(
            "AAAA",
            "BBCD",
            "BBCC",
            "EEEC"
    );
    List<String> input2 = Arrays.asList(
            "OOOOO",
            "OXOXO",
            "OOOOO",
            "OXOXO",
            "OOOOO"
    );
    List<String> input3 = Arrays.asList(
            "RRRRIICCFF",
            "RRRRIICCCF",
            "VVRRRCCFFF",
            "VVRCCCJFFF",
            "VVVVCJJCFE",
            "VVIVCCJJEE",
            "VVIIICJJEE",
            "MIIIIIJJEE",
            "MIIISIJEEE",
            "MMMISSJEEE"
    );

    @Test
    void testPart1() {
        assertEquals(140, Day12.part1(input1));
        assertEquals(772, Day12.part1(input2));
        assertEquals(1930, Day12.part1(input3));
    }

    @Test
    void testPart2() {
        assertEquals(80, Day12.part2(input1));
        assertEquals(436, Day12.part2(input2));
        assertEquals(1206, Day12.part2(input3));
    }
}
