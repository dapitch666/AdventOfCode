package org.anne.aoc2015;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day25Test {

    @Test
    void testPart1() {
        assertEquals(30943339, Day25.part1(List.of(1, 4)));
    }

    @Test
    void testPart2() {
        assertEquals("Merry Christmas!", Day25.part2());
    }
}
