package org.anne.aoc2021;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day12Test {
    final List<String> input = List.of(
            "fs-end",
            "he-DX",
            "fs-he",
            "start-DX",
            "pj-DX",
            "end-zg",
            "zg-sl",
            "zg-pj",
            "pj-he",
            "RW-he",
            "fs-DX",
            "pj-RW",
            "zg-RW",
            "start-pj",
            "he-WI",
            "zg-he",
            "pj-fs",
            "start-RW"
    );

    @Test
    void testPart1() {
        assertEquals(226, Day12.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(3509, Day12.part2(input));
    }
}