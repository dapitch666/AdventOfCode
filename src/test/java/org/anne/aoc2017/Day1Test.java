package org.anne.aoc2017;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day1Test {

    @Test
    void testPart1() {
        assertEquals(3, Day1.part1("1122"));
        assertEquals(4, Day1.part1("1111"));
        assertEquals(0, Day1.part1("1234"));
        assertEquals(9, Day1.part1("91212129"));
    }

    @Test
    void testPart2() {
        assertEquals(6, Day1.part2("1212"));
        assertEquals(0, Day1.part2("1221"));
        assertEquals(4, Day1.part2("123425"));
        assertEquals(12, Day1.part2("123123"));
        assertEquals(4, Day1.part2("12131415"));
    }
}
