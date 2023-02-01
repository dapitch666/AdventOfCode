package org.anne.aoc2020;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day14Test {

    List<String> input = List.of(
            "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X",
            "mem[8] = 11",
            "mem[7] = 101",
            "mem[8] = 0"
    );

    List<String> input2 = List.of(
            "mask = 000000000000000000000000000000X1001X",
            "mem[42] = 100",
            "mask = 00000000000000000000000000000000X0XX",
            "mem[26] = 1"
    );

    @Test
    void testPart1() {
        assertEquals(165, Day14.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(208, Day14.part2(input2));
    }
}