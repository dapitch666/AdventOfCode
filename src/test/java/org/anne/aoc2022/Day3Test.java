package org.anne.aoc2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day3Test {
    final List<String> list = Arrays.asList(
            "vJrwpWtwJgWrhcsFMMfFFhFp",
            "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
            "PmmdzqPrVvPwwTWBwg",
            "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
            "ttgJtRGJQctTZtZT",
            "CrZsJsPPZsGzwwsLwLmpwMDw"
    );

    @Test
    void testPart1() {
        assertEquals(157, Day3.part1(list));
    }

    @Test
    void testValueOfChar() {
        System.out.println(Day3.valueOfChar('Z'));
        assertEquals(16, Day3.valueOfChar('p'));
        assertEquals(38, Day3.valueOfChar('L'));
    }

    @Test
    void testPart2() {
        assertEquals(70, Day3.part2(list));
    }
}
