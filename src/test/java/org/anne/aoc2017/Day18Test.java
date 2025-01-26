package org.anne.aoc2017;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day18Test {

    List<String> input = Arrays.asList(
            "set a 1",
            "add a 2",
            "mul a a",
            "mod a 5",
            "snd a",
            "set a 0",
            "rcv a",
            "jgz a -1",
            "set a 1",
            "jgz a -2"
    );

    List<String> input2 = Arrays.asList(
            "snd 1",
            "snd 2",
            "snd p",
            "rcv a",
            "rcv b",
            "rcv c",
            "rcv d"
    );

    @Test
    void testPart1() {
        assertEquals(4, Day18.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(3, Day18.part2(input2));
    }
}
