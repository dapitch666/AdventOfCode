package org.anne.aoc2020;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day2Test {
    final List<String> input = List.of(
            "1-3 a: abcde",
            "1-3 b: cdefg",
            "2-9 c: ccccccccc"
    );

    @Test
    void testPart1() {
        assertEquals(2, Day2.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(1, Day2.part2(input));
    }
}
