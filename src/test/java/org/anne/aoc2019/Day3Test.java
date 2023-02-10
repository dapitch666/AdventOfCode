package org.anne.aoc2019;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class Day3Test {
    Map<Point, Integer> wire1a = Day3.mapWire("R8,U5,L5,D3");
    Map<Point, Integer> wire1b = Day3.mapWire("U7,R6,D4,L4");

    Map<Point, Integer> wire2a = Day3.mapWire("R75,D30,R83,U83,L12,D49,R71,U7,L72");
    Map<Point, Integer> wire2b = Day3.mapWire("U62,R66,U55,R34,D71,R55,D58,R83");

    Map<Point, Integer> wire3a = Day3.mapWire("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51");
    Map<Point, Integer> wire3b = Day3.mapWire("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7");

    @Test
    void testPart1() {
        assertEquals(6, Day3.part1(wire1a, wire1b));
        assertEquals(159, Day3.part1(wire2a, wire2b));
        assertEquals(135, Day3.part1(wire3a, wire3b));
    }

    @Test
    void testPart2() {
        assertEquals(30, Day3.part2(wire1a, wire1b));
        assertEquals(610, Day3.part2(wire2a, wire2b));
        assertEquals(410, Day3.part2(wire3a, wire3b));
    }
}