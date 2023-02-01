package org.anne.aoc2021;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day11Test {
    final List<String> input = List.of(
            "5483143223",
            "2745854711",
            "5264556173",
            "6141336146",
            "6357385478",
            "4167524645",
            "2176841721",
            "6882881134",
            "4846848554",
            "5283751526"
    );
    final List<String> miniInput = List.of(
            "11111",
            "19991",
            "19191",
            "19991",
            "11111"
    );

    @Test
    void testPart1() {
        assertEquals(9, Day11.part1(miniInput, 2));
        assertEquals(1656, Day11.part1(input, 100));
    }

    @Test
    void testPart2() {
        assertEquals(195, Day11.part2(input));

    }
}