package org.anne.aoc2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day4Test {
    List<String> input = Arrays.asList(
            "2-4,6-8",
            "2-3,4-5",
            "5-7,7-9",
            "2-8,3-7",
            "6-6,4-6",
            "2-6,4-8"
    );

    @Test
    void part1() {
        assertEquals(2, Day4.part1(input));
    }

    @Test
    void part2() {
        assertEquals(4, Day4.part2(input));
    }
}