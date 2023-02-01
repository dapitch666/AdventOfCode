package org.anne.aoc2020;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day9Test {

    List<Long> input = List.of(35L, 20L, 15L, 25L, 47L, 40L, 62L, 55L, 65L, 95L, 102L, 117L, 150L, 182L, 127L, 219L,
            299L, 277L, 309L, 576L);
    @Test
    void testPart1() {
        assertEquals(127L, Day9.part1(input, 5));
    }

    @Test
    void testPart2() {
        assertEquals(62, Day9.part2(input, 5));
    }
}