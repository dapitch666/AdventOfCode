package org.anne.aoc2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day24Test {
    final List<String> input = Arrays.asList(
            "#.######",
            "#>>.<^<#",
            "#.<..<<#",
            "#>v.><>#",
            "#<^v^^>#",
            "######.#"
    );

    @Test
    void part1() {
        assertEquals(18, Day24.part1(input));
    }

    @Test
    void part2() {
        assertEquals(54, Day24.part2(input));
    }
}