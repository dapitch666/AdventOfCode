package org.anne.aoc2016;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day8Test {

    List<String> input = Arrays.asList(
            "rect 3x2",
            "rotate column x=1 by 1",
            "rotate row y=0 by 4",
            "rotate column x=1 by 1"
    );

    @Test
    void testPart1() {
        assertEquals(6, Day8.part1(input, 7, 3));
    }
}
