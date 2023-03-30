package org.anne.aoc2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day11Test {
    final List<String> input = Arrays.asList(
            "Monkey 0:",
            "Starting items: 79, 98",
            "Operation: new = old * 19",
            "Test: divisible by 23",
            "If true: throw to monkey 2",
            "If false: throw to monkey 3",
            "",
            "Monkey 1:",
            "Starting items: 54, 65, 75, 74",
            "Operation: new = old + 6",
            "Test: divisible by 19",
            "If true: throw to monkey 2",
            "If false: throw to monkey 0",
            "",
            "Monkey 2:",
            "Starting items: 79, 60, 97",
            "Operation: new = old * old",
            "Test: divisible by 13",
            "If true: throw to monkey 1",
            "If false: throw to monkey 3",
            "",
            "Monkey 3:",
            "Starting items: 74",
            "Operation: new = old + 3",
            "Test: divisible by 17",
            "If true: throw to monkey 0",
            "If false: throw to monkey 1"
    );

    @Test
    void part1() {
        assertEquals(10605, Day11.part1(input));
    }

    @Test
    void part2() {
        assertEquals(2713310158L, Day11.part2(input));
    }
}