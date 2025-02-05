package org.anne.aoc2016;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Test {

    List<String> input = Arrays.asList(
            "cpy 41 a",
            "inc a",
            "inc a",
            "dec a",
            "jnz a 2",
            "dec a"
    );

    @Test
    void testPart1() {
        assertEquals(42, Day12.part1(input));
    }
}
