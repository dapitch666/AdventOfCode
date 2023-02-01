package org.anne.aoc2021;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day23Test {
    final List<String> input = List.of(
            "#############",
            "#...........#",
            "###B#C#B#D###",
            "  #A#D#C#A#  ",
            "  #########  "
    );

    @Test
    void testPart1() {
        assertEquals(12521, Day23.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(44169, Day23.part2(input));
    }
}