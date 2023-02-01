package org.anne.aoc2021;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day15Test {
    final List<String> input = List.of(
            "1163751742",
            "1381373672",
            "2136511328",
            "3694931569",
            "7463417111",
            "1319128137",
            "1359912421",
            "3125421639",
            "1293138521",
            "2311944581"
    );

    final List<String> smallInput = List.of(
            "12",
            "34"
    );

    final List<String> mediumInput = List.of(
            "1223",
            "3445",
            "2334",
            "4556"
    );

    @Test
    void testPart1() {
        assertEquals(6L, Day15.part1(smallInput));
        assertEquals(40L, Day15.part1(input));
        assertEquals(21L, Day15.part1(mediumInput));
    }

    @Test
    void testPart2() {
        assertEquals(73L, Day15.part2(smallInput));
        assertEquals(315L, Day15.part2(input));
    }
}