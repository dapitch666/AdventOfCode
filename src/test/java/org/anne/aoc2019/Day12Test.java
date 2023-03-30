package org.anne.aoc2019;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day12Test {

    final List<String> input = List.of(
            "<x=-1, y=0, z=2>",
            "<x=2, y=-10, z=-7>",
            "<x=4, y=-8, z=8>",
            "<x=3, y=5, z=-1>"
    );

    @Test
    void testPart1() {
        assertEquals(179, Day12.part1(input, 10));
    }

    @Test
    void testPart2() {
        assertEquals(2772, Day12.part2(input));
    }
}