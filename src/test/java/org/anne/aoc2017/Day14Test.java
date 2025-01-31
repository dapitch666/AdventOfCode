package org.anne.aoc2017;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day14Test {

    @Test
    void testPart1() {
        assertEquals(8108, Day14.part1("flqrgnkx"));
    }

    @Test
    void testPart2() {
        assertEquals(1242, Day14.part2("flqrgnkx"));
    }
}
