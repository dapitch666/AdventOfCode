package org.anne.aoc2024;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day18Test {

    List<String> input = Arrays.asList(
            "5,4",
            "4,2",
            "4,5",
            "3,0",
            "2,1",
            "6,3",
            "2,4",
            "1,5",
            "0,6",
            "3,3",
            "2,6",
            "5,1",
            "1,2",
            "5,5",
            "2,5",
            "6,5",
            "1,4",
            "0,4",
            "6,4",
            "1,1",
            "6,1",
            "1,0",
            "0,5",
            "1,6",
            "2,0"
    );

    @Test
    void testPart1() {
        assertEquals(22, Day18.part1(input, 7));
    }

    @Test
    void testPart2() {
        assertEquals("6,1", Day18.part2(input, 7));
    }
}
