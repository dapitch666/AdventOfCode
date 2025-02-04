package org.anne.aoc2016;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10Test {

    List<String> input = Arrays.asList(
            "value 5 goes to bot 2",
            "bot 2 gives low to bot 1 and high to bot 0",
            "value 3 goes to bot 1",
            "bot 1 gives low to output 1 and high to bot 0",
            "bot 0 gives low to output 2 and high to output 0",
            "value 2 goes to bot 2"
    );

    @Test
    void testPart1() {
        assertEquals(2, Day10.part1(input, 5, 2));
    }

    @Test
    void testPart2() {
        assertEquals(30, Day10.part2(input));
    }
}
