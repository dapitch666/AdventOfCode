package org.anne.aoc2020;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day8Test {

    List<String> input = List.of(
            "nop +0",
            "acc +1",
            "jmp +4",
            "acc +3",
            "jmp -3",
            "acc -99",
            "acc +1",
            "jmp -4",
            "acc +6"
    );

    @Test
    void testPart1() {
        assertEquals(5, Day8.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(8, Day8.part2(input));
    }
}