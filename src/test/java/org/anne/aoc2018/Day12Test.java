package org.anne.aoc2018;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Test {

    List<String> input = Arrays.asList(
            "initial state: #..#.#..##......###...###",
            "",
            "...## => #",
            "..#.. => #",
            ".#... => #",
            ".#.#. => #",
            ".#.## => #",
            ".##.. => #",
            ".#### => #",
            "#.#.# => #",
            "#.### => #",
            "##.#. => #",
            "##.## => #",
            "###.. => #",
            "###.# => #",
            "####. => #"
    );

    @Test
    void testPart1() {
        assertEquals(325, Day12.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(50000000501L, Day12.part2(input));
    }
}
