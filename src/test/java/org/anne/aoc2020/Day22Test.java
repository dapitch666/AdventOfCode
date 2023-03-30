package org.anne.aoc2020;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day22Test {
    final List<String> input = List.of(
            "Player 1:",
            "9",
            "2",
            "6",
            "3",
            "1",
            "",
            "Player 2:",
            "5",
            "8",
            "4",
            "7",
            "10"
    );

    @Test
    void testPart1() {
        assertEquals(306, Day22.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(291, Day22.part2(input));
    }
}