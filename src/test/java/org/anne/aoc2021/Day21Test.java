package org.anne.aoc2021;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day21Test {
    final List<String> input = List.of(
            "Player 1 starting position: 4",
            "Player 2 starting position: 8"
    );

    @Test
    void testPart1() {
        assertEquals(739785L, Day21.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(444356092776315L, Day21.part2(input));
    }
}