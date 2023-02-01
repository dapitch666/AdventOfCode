package org.anne.aoc2020;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day6Test {

    List<String> input = new LinkedList<>(List.of("abc", "", "a", "b", "c", "", "ab", "ac", "", "a", "a", "a", "", "b"));

    @Test
    void part1() {
        assertEquals(11, Day6.part1(input));
    }

    @Test
    void part2() {
        assertEquals(6, Day6.part2(input));
    }
}