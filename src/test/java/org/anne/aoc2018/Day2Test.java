package org.anne.aoc2018;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day2Test {

    List<String> input = Arrays.asList(
            "abcdef",
            "bababc",
            "abbcde",
            "abcccd",
            "aabcdd",
            "abcdee",
            "ababab"
    );

    List<String> input2 = Arrays.asList(
            "abcde",
            "fghij",
            "klmno",
            "pqrst",
            "fguij",
            "axcye",
            "wvxyz"
    );

    @Test
    void testPart1() {
        assertEquals(12, Day2.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals("fgij", Day2.part2(input2));
    }
}
