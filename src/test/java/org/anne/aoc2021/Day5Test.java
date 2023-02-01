package org.anne.aoc2021;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class Day5Test {
    final List<String> input = List.of(
            "0,9 -> 5,9",
            "8,0 -> 0,8",
            "9,4 -> 3,4",
            "2,2 -> 2,1",
            "7,0 -> 7,4",
            "6,4 -> 2,0",
            "0,9 -> 2,9",
            "3,4 -> 1,4",
            "0,0 -> 8,8",
            "5,5 -> 8,2"
    );

    @Test
    void testPart1() {
        String pattern = "([0-9]+),([0-9]) -> ([0-9]),([0-9])";
        String s = "812,0 -> 0,8";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(s);
        while(m.find()) {
            assertEquals("8", m.group(4));
        }
        assertTrue(s.matches(pattern));
        assertEquals(5L, Day5.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(12L, Day5.part2(input));
    }
}