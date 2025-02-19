package org.anne.aoc2015;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day23Test {

    List<String> input = Arrays.asList(
            "inc a",
            "jio a, +2",
            "tpl a",
            "inc a"
    );

    @Test
    void testPart1() {
        assertEquals(2, Day23.part1(input, 'a'));
    }

    @Test
    void testPart2() {
        assertEquals(7, Day23.part2(input, 'a'));
    }
}
