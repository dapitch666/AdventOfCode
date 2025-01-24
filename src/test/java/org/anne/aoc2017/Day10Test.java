package org.anne.aoc2017;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10Test {

    @Test
    void testPart1() {
        assertEquals(12, Day10.part1("3,4,1,5", 5));
    }

    @Test
    void testPart2() {
        assertEquals("a2582a3a0e66e6e86e3812dcb672a272", Day10.part2(""));
        assertEquals("33efeb34ea91902bb2f59c9920caa6cd", Day10.part2("AoC 2017"));
        assertEquals("3efbe78a8d82f29979031a4aa0b16a9d", Day10.part2("1,2,3"));
        assertEquals("63960835bcdc130f0b66d7ff4f6a5a8e", Day10.part2("1,2,4"));
    }
}
