package org.anne.aoc2015;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day6Test {

    List<String> input = Arrays.asList(
            "turn on 0,0 through 999,999",
            "toggle 0,0 through 999,0",
            "turn off 499,499 through 500,500"
    );

    @Test
    void testPart1() {
        assertEquals(998996, Day6.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(1, Day6.part2(List.of("turn on 0,0 through 0,0")));
        assertEquals(2000000, Day6.part2(List.of("toggle 0,0 through 999,999")));
    }
}
