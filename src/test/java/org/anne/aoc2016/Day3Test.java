package org.anne.aoc2016;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day3Test {

    List<String> input = List.of("5 10 25");

    @Test
    void testPart1() {
        assertEquals(0, Day3.part1(input));
    }
}
