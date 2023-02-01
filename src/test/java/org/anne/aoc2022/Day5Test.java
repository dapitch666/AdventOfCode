package org.anne.aoc2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day5Test {
    List<String> input = Arrays.asList(
    "    [D]",
    "[N] [C]",
    "[Z] [M] [P]",
    " 1   2   3",
    "",
    "move 1 from 2 to 1",
    "move 3 from 1 to 3",
    "move 2 from 2 to 1",
    "move 1 from 1 to 2"
    );

    @Test
    void testPart1() {
        System.out.println(input.indexOf(""));
        assertEquals("CMZ", Day5.part1(input));
    }

    @Test
    void testPart2() {
        System.out.println(input.indexOf(""));
        assertEquals("MCD", Day5.part2(input));
    }
}
