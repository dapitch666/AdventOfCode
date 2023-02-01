package org.anne.aoc2022;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day17Test {
    String input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>";

    @Test
    void part1() {
        assertEquals(3068, Day17.part1(input));
    }

    @Test
    void part2() {
        assertEquals(1514285714288L, Day17.part2(input));
    }
}