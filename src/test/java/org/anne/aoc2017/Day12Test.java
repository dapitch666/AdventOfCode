package org.anne.aoc2017;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Test {

    List<String> input = Arrays.asList(
            "0 <-> 2",
            "1 <-> 1",
            "2 <-> 0, 3, 4",
            "3 <-> 2, 4",
            "4 <-> 2, 3, 6",
            "5 <-> 6",
            "6 <-> 4, 5"
    );

    @Test
    void testPart1() {
        assertEquals(6, Day12.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(2, Day12.part2(input));
    }
}
