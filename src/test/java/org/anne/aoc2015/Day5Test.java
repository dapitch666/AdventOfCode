package org.anne.aoc2015;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day5Test {

    List<String> input = Arrays.asList(
            "ugknbfddgicrmopn",
            "aaa",
            "jchzalrnumimnmhp",
            "haegwjzuvuyypxyu",
            "dvszwmarrgswjxmb"
    );

    List<String> input2 = Arrays.asList(
            "qjhvhtzxzqqjkmpb",
            "xxyxx",
            "uurcxstgmygtbstg",
            "ieodomkazucvgmuy"
    );

    @Test
    void testPart1() {
        assertEquals(2, Day5.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(0, Day5.part2(input));
    }
}
