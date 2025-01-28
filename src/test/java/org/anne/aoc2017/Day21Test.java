package org.anne.aoc2017;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day21Test {

    List<String> input = Arrays.asList(
            "../.# => ##./#../...",
            ".#./..#/### => #..#/..../..../#..#"
    );

    @Test
    void testPart1() {
        assertEquals(12, Day21.part1(input, 2));
    }
}
