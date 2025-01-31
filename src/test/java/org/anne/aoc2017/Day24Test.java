package org.anne.aoc2017;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day24Test {

    List<String> input = Arrays.asList(
            "0/2",
            "2/2",
            "2/3",
            "3/4",
            "3/5",
            "0/1",
            "10/1",
            "9/10"
    );

    @Test
    void testPart1() {
        assertEquals(31, Day24.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(19, Day24.part2(input));
    }
}
