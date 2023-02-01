package org.anne.aoc2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day20Test {
    List<Integer> input = Arrays.asList(1, 2, -3, 3, -2, 0, 4);

    @Test
    void part1() {
        assertEquals(3, Day20.part1(input));
    }

    @Test
    void part2() {
        assertEquals(1623178306, Day20.part2(input));
    }
}