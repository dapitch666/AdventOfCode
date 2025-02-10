package org.anne.aoc2016;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day23Test {

    List<String> input = Arrays.asList(
            "cpy 2 a",
            "tgl a",
            "tgl a",
            "tgl a",
            "cpy 1 a",
            "dec a",
            "dec a"
    );

    @Test
    void testPart1() {
        assertEquals(3, Day23.part1(input));
    }

}
