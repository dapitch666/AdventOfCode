package org.anne.aoc2015;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day9Test {

    List<String> input = Arrays.asList(
            "London to Dublin = 464",
            "London to Belfast = 518",
            "Dublin to Belfast = 141"
    );

    @Test
    void testPart1() {
        assertEquals(605, Day9.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(982, Day9.part2(input));
    }
}
