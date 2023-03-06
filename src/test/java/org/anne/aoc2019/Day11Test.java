package org.anne.aoc2019;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class Day11Test {

    @Test
    void testPart1() {
        assertEquals(Day11.Direction.LEFT, Day11.turn(Day11.Direction.UP, 0));
        assertEquals(Day11.Direction.DOWN, Day11.turn(Day11.Direction.LEFT, 0));
        assertEquals(Day11.Direction.RIGHT, Day11.turn(Day11.Direction.DOWN, 0));
        assertEquals(Day11.Direction.UP, Day11.turn(Day11.Direction.RIGHT, 0));
        assertEquals(Day11.Direction.RIGHT, Day11.turn(Day11.Direction.UP, 1));
        assertEquals(Day11.Direction.UP, Day11.turn(Day11.Direction.LEFT, 1));
        assertEquals(Day11.Direction.LEFT, Day11.turn(Day11.Direction.DOWN, 1));
        assertEquals(Day11.Direction.DOWN, Day11.turn(Day11.Direction.RIGHT, 1));
    }

    @Test
    void testPart2() {
    }
}