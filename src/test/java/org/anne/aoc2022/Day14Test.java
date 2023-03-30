package org.anne.aoc2022;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day14Test {
    final List<String> input = Arrays.asList(
            "498,4 -> 498,6 -> 496,6",
            "503,4 -> 502,4 -> 502,9 -> 494,9"
    );

    @Test
    void part1() {
        assertEquals(24, Day14.part1(input));
    }

    @Test
    void part2() {
        assertEquals(93, Day14.part2(input));
    }

    @Test
    void drawCaveTest() {
        Set<Point> points = Day14.drawCave(input);
        int floor = points.stream().mapToInt(p -> p.y).max().orElseThrow() + 2;
        for (int i = 500 - floor; i < 500 + floor; i++) {
            points.add(new Point(i, floor));
        }
        assertTrue(points.contains(new Point(502, 7)));
    }
}