package org.anne.aoc2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day18Test {
    final List<String> input = Arrays.asList(
            "2,2,2",
            "1,2,2",
            "3,2,2",
            "2,1,2",
            "2,3,2",
            "2,2,1",
            "2,2,3",
            "2,2,4",
            "2,2,6",
            "1,2,5",
            "3,2,5",
            "2,1,5",
            "2,3,5"
    );

    @Test
    void part1() {
        assertEquals(64, Day18.part1(input));
    }

    @Test
    void part2() {
        assertEquals(58, Day18.part2(input));
    }
}