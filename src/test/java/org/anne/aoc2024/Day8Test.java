package org.anne.aoc2024;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day8Test {

    List<String> input = Arrays.asList(
            "............",
            "........0...",
            ".....0......",
            ".......0....",
            "....0.......",
            "......A.....",
            "............",
            "............",
            "........A...",
            ".........A..",
            "............",
            "............"
    );

    @Test
    void testPart1() {
        assertEquals(14, Day8.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(34, Day8.part2(input));
    }
}
