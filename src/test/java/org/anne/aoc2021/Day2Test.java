package org.anne.aoc2021;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day2Test {
    final List<String> commands = List.of("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2");

    @Test
    void testPart1() {
        assertEquals(150L, Day2.calculatePosition(commands, 1));
    }

    @Test
    void testPart2() {
        assertEquals(900L, Day2.calculatePosition(commands, 2));
    }
}