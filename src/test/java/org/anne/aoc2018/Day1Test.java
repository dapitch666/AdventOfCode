package org.anne.aoc2018;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day1Test {

    List<Integer> input = Arrays.asList(
            +1,
            -2,
            +3,
            +1
    );

    List<Integer> input2 = Arrays.asList(
            +1,
            -1
    );

    List<Integer> input3 = Arrays.asList(
            +3,
            +3,
            +4,
            -2,
            -4
    );

    List<Integer> input4 = Arrays.asList(
            -6,
            +3,
            +8,
            +5,
            -6
    );

    List<Integer> input5 = Arrays.asList(
            +7,
            +7,
            -2,
            -7,
            -4
    );

    @Test
    void testPart1() {
        assertEquals(3, Day1.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(0, Day1.part2(input2));
        assertEquals(10, Day1.part2(input3));
        assertEquals(5, Day1.part2(input4));
        assertEquals(14, Day1.part2(input5));
    }
}
