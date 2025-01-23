package org.anne.aoc2017;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day8Test {

    List<String> input = Arrays.asList(
            "b inc 5 if a > 1",
            "a inc 1 if b < 5",
            "c dec -10 if a >= 1",
            "c inc -20 if c == 10"
    );

    @Test
    void testPart1() {
        assertEquals(1, Day8.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(10, Day8.part2(input));
    }
}
