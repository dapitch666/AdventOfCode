package org.anne.aoc2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day19Test {
    final List<String> input = Arrays.asList(
            "Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.",
            "Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 8 clay. Each geode robot costs 3 ore and 12 obsidian."
    );

    @Test
    void part1() {
        assertEquals(33, Day19.part1(input));
    }

    @Test
    void part2() {
        assertEquals(3472, Day19.part2(input));
    }
}