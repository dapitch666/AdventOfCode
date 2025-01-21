package org.anne.aoc2017;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day2Test {

    List<String> input = Arrays.asList(
            "5 1 9 5",
            "7 5 3",
            "2 4 6 8"
    );

    List<String> input2 = Arrays.asList(
            "5 9 2 8",
            "9 4 7 3",
            "3 8 6 5"
    );

    @Test
    void testPart1() {
        assertEquals(18, Day2.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(9, Day2.part2(input2));
    }
}
