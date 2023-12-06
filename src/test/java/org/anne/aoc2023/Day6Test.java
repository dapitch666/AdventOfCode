package org.anne.aoc2023;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day6Test {

    List<String> input = Arrays.asList(
            "Time:      7  15   30",
            "Distance:  9  40  200"
    );
    
    @Test
    void testPart1() {
        assertEquals(288, Day6.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(71503, Day6.part2(input));
    }
}
