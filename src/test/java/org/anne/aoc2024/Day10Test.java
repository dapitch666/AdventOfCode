package org.anne.aoc2024;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10Test {

    List<String> input = Arrays.asList(
            "89010123",
            "78121874",
            "87430965",
            "96549874",
            "45678903",
            "32019012",
            "01329801",
            "10456732"
    );

    @Test
    void testPart1() {
        assertEquals(36, Day10.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(81, Day10.part2(input));
    }
}
