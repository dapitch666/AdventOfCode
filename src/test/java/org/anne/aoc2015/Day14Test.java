package org.anne.aoc2015;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day14Test {

    List<String> input = Arrays.asList(
            "Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.",
            "Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds."
    );

    @Test
    void testPart1() {
        assertEquals(1120, Day14.part1(input, 1000));
    }

    @Test
    void testPart2() {
        assertEquals(689, Day14.part2(input, 1000));
    }
}
