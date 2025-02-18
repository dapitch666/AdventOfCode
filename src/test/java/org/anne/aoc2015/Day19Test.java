package org.anne.aoc2015;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day19Test {

    List<String> input = Arrays.asList(
            "H => HO",
            "H => OH",
            "O => HH",
            "",
            "HOH"
    );

    List<String> input2 = Arrays.asList(
            "H => HO",
            "H => OH",
            "O => HH",
            "",
            "HOHOHO"
    );

    List<String> input3 = Arrays.asList(
            "H => HO",
            "H => OH",
            "O => HH",
            "e => H",
            "e => O",
            "",
            "HOH"
    );

    @Test
    void testPart1() {
        assertEquals(4, Day19.part1(input));
        assertEquals(7, Day19.part1(input2));
    }

    @Test
    void testPart2() {
        assertEquals(3, Day19.part2(input3));
    }
}
