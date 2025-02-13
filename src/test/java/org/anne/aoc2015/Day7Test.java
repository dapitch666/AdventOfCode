package org.anne.aoc2015;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day7Test {

    List<String> input = Arrays.asList(
            "123 -> x",
            "456 -> y",
            "x AND y -> d",
            "x OR y -> e",
            "x LSHIFT 2 -> f",
            "y RSHIFT 2 -> g",
            "NOT x -> h",
            "NOT y -> i"
    );

    @Test
    void testPart1() {
        assertEquals(72, Day7.part1(input, "d"));
        assertEquals(507, Day7.part1(input, "e"));
        assertEquals(492, Day7.part1(input, "f"));
        assertEquals(114, Day7.part1(input, "g"));
        assertEquals(65412, Day7.part1(input, "h"));
        assertEquals(65079, Day7.part1(input, "i"));
        assertEquals(123, Day7.part1(input, "x"));
        assertEquals(456, Day7.part1(input, "y"));
    }
}
