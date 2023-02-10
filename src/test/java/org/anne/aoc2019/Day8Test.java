package org.anne.aoc2019;

import org.anne.common.Constants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day8Test {

    @Test
    void testPart1() {
        assertEquals(1, Day8.part1("123456789012", 3, 2));
        assertEquals(6, Day8.part1("121412789012", 3, 2));
    }

    @Test
    void testPart2() {
        String result = "  ##" + Constants.LINE_SEPARATOR + "##  " + Constants.LINE_SEPARATOR;
        assertEquals(result, Day8.part2("0222112222120000", 2, 2));
    }
}