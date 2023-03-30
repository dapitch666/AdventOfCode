package org.anne.aoc2019;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day15Test {
    static final List<String> input = List.of(
            " ##",
            "#..##",
            "#.#..#",
            "#.O.#",
            " ###"
    );

    static final Map<Point, Character> map = new HashMap<>();

    @BeforeAll
    static void setup() {
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length(); x++) {
                map.put(new Point(x, y), input.get(y).charAt(x));
            }
        }
    }

    @Test
    void testFindNeighbor() {
        assertEquals(new Point(2, 1), Day15.findNeighbor(map, new Point(1, 1)));
    }

    @Test
    void testPart2() {
        assertEquals(4, Day15.minutesToFill(map));
    }
}