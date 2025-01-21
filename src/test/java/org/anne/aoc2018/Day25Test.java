package org.anne.aoc2018;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day25Test {
    List<String> input1 = Arrays.asList(
            "0,0,0,0",
            "3,0,0,0",
            "0,3,0,0",
            "0,0,3,0",
            "0,0,0,3",
            "0,0,0,6",
            "9,0,0,0",
            "12,0,0,0"
    );
    List<String> input2 = Arrays.asList(
            "-1,2,2,0",
            "0,0,2,-2",
            "0,0,0,-2",
            "-1,2,0,0",
            "-2,-2,-2,2",
            "3,0,2,-1",
            "-1,3,2,2",
            "-1,0,-1,0",
            "0,2,1,-2",
            "3,0,0,0"
    );
    List<String> input3 = Arrays.asList(
            "1,-1,0,1",
            "2,0,-1,0",
            "3,2,-1,0",
            "0,0,3,1",
            "0,0,-1,-1",
            "2,3,-2,0",
            "-2,2,0,0",
            "2,-2,0,-1",
            "1,-1,0,-1",
            "3,2,0,2"
    );
    List<String> input4 = Arrays.asList(
            "1,-1,-1,-2",
            "-2,-2,0,1",
            "0,2,1,3",
            "-2,3,-2,1",
            "0,2,3,-2",
            "-1,-1,1,-2",
            "0,-2,-1,0",
            "-2,2,3,-1",
            "1,2,2,0",
            "-1,-2,0,-2"
    );

    @Test
    void testPart1() {
        assertEquals(2, Day25.part1(input1));
        assertEquals(4, Day25.part1(input2));
        assertEquals(3, Day25.part1(input3));
        assertEquals(8, Day25.part1(input4));
    }

    @Test
    void testPart2() {
        assertEquals("Merry Christmas!", Day25.part2());
    }
}
