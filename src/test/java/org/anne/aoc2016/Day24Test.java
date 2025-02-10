package org.anne.aoc2016;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day24Test {

    List<String> input = Arrays.asList(
            "###########",
            "#0.1.....2#",
            "#.#######.#",
            "#4.......3#",
            "###########"
    );

    @Test
    void testPart1() {
        assertEquals(14, Day24.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(20, Day24.part2(input));
    }
}
