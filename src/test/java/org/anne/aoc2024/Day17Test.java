package org.anne.aoc2024;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day17Test {

    List<String> input = Arrays.asList(
            "Register A: 729",
            "Register B: 0",
            "Register C: 0",
            "",
            "Program: 0,1,5,4,3,0"
    );

    List<String> input2 = Arrays.asList(
            "Register A: 2024",
            "Register B: 0",
            "Register C: 0",
            "",
            "Program: 0,3,5,4,3,0"
    );

    @Test
    void testPart1() {
        assertEquals("4,6,3,5,6,3,5,2,1,0", Day17.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(117440, Day17.part2(input2));
    }
}
