package org.anne.aoc2018;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day7Test {

    List<String> input = Arrays.asList(
            "Step C must be finished before step A can begin.",
            "Step C must be finished before step F can begin.",
            "Step A must be finished before step B can begin.",
            "Step A must be finished before step D can begin.",
            "Step B must be finished before step E can begin.",
            "Step D must be finished before step E can begin.",
            "Step F must be finished before step E can begin."
    );

    @Test
    void testPart1() {
        assertEquals("CABDFE", Day7.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(258, Day7.part2(input, 2));
    }
}
