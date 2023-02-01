package org.anne.aoc2020;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day18Test {

    @Test
    void testPart1() {
        assertEquals(71, Day18.calculate("1 + 2 * 3 + 4 * 5 + 6", false));
        assertEquals(51, Day18.calculate("1 + (2 * 3) + (4 * (5 + 6))", false));
        assertEquals(26, Day18.calculate("2 * 3 + (4 * 5)", false));
        assertEquals(437, Day18.calculate("5 + (8 * 3 + 9 + 3 * 4 * 3)", false));
        assertEquals(12240, Day18.calculate("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))", false));
        assertEquals(13632, Day18.calculate("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", false));
    }

    @Test
    void testPart2() {
        assertEquals(231, Day18.calculate("1 + 2 * 3 + 4 * 5 + 6", true));
        assertEquals(51, Day18.calculate("1 + (2 * 3) + (4 * (5 + 6))", true));
        assertEquals(46, Day18.calculate("2 * 3 + (4 * 5)", true));
        assertEquals(1445, Day18.calculate("5 + (8 * 3 + 9 + 3 * 4 * 3)", true));
        assertEquals(669060, Day18.calculate("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))", true));
        assertEquals(23340, Day18.calculate("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2", true));
    }
}