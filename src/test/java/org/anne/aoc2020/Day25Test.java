package org.anne.aoc2020;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day25Test {
    final List<Integer> input = List.of(5764801, 17807724);

    @Test
    void part1() {
        assertEquals(14897079, Day25.part1(input));
    }

    @Test
    void part2() {
        assertEquals("Merry Christmas!", Day25.part2());
    }
}