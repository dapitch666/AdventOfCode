package org.anne.aoc2018;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day14Test {

    @Test
    void testPart1() {
        assertEquals("5158916779", Day14.part1(9));
        assertEquals("0124515891", Day14.part1(5));
        assertEquals("9251071085", Day14.part1(18));
        assertEquals("5941429882", Day14.part1(2018));
    }

    @Test
    void testPart2() {
        assertEquals(9, Day14.part2("51589"));
        assertEquals(5, Day14.part2("01245"));
        assertEquals(18, Day14.part2("92510"));
        assertEquals(2018, Day14.part2("59414"));
    }
}
