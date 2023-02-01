package org.anne.aoc2020;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day23Test {

    @Test
    void testPart1() {
        assertEquals("67384529", Day23.part1("389125467"));
    }

    @Test
    void testPart2() {
        assertEquals(149245887792L, Day23.part2("389125467"));
    }
}