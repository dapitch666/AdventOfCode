package org.anne.aoc2021;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.*;

class Day17Test {

    @Test
    void testPart1() {
        assertEquals(45, Day17.part1("target area: x=20..30, y=-10..-5"));
    }

    @Test
    void testPart2() {
        assertEquals(112, Day17.part2("target area: x=20..30, y=-10..-5"));
    }

    @Test
    void testRectangle() {
        Rectangle target = new Rectangle(20,-10, 11, 6);
        assertEquals(target, Day17.getTarget("target area: x=20..30, y=-10..-5"));
        assertEquals(OptionalInt.of(3), Day17.getHighestPoint(target, new Point(7,2)));
        assertEquals(OptionalInt.of(6), Day17.getHighestPoint(target, new Point(6,3)));
        assertEquals(OptionalInt.of(0), Day17.getHighestPoint(target, new Point(9,0)));
        assertEquals(OptionalInt.empty(), Day17.getHighestPoint(target, new Point(17,-4)));
    }
}