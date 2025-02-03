package org.anne.aoc2016;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day9Test {

    @Test
    void testPart1() {
        assertEquals(6, Day9.part1("ADVENT"));
        assertEquals(7, Day9.part1("A(1x5)BC"));
        assertEquals(9, Day9.part1("(3x3)XYZ"));
        assertEquals(11, Day9.part1("A(2x2)BCD(2x2)EFG"));
        assertEquals(6, Day9.part1("(6x1)(1x3)A"));
        assertEquals(18, Day9.part1("X(8x2)(3x3)ABCY"));
    }

    @Test
    void testPart2() {
        assertEquals(9, Day9.part2("(3x3)XYZ"));
        assertEquals(20, Day9.part2("X(8x2)(3x3)ABCY"));
        assertEquals(241920, Day9.part2("(27x12)(20x12)(13x14)(7x10)(1x12)A"));
        assertEquals(445, Day9.part2("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN"));
    }
}
