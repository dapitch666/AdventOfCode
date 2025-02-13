package org.anne.aoc2015;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day1Test {

    @Test
    void testPart1() {
        assertEquals(0, Day1.part1("(())"));
        assertEquals(0, Day1.part1("()()"));
        assertEquals(3, Day1.part1("((("));
        assertEquals(3, Day1.part1("(()(()("));
        assertEquals(3, Day1.part1("))((((("));
        assertEquals(-1, Day1.part1("())"));
        assertEquals(-1, Day1.part1("))("));
        assertEquals(-3, Day1.part1(")))"));
        assertEquals(-3, Day1.part1(")())())"));
    }

    @Test
    void testPart2() {
        assertEquals(1, Day1.part2(")"));
        assertEquals(5, Day1.part2("()())"));
    }
}
