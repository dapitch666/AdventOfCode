package org.anne.aoc2024;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day3Test {

    String input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";

    @Test
    void testPart1() {
        assertEquals(161, Day3.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(48, Day3.part2(input));
    }
}
