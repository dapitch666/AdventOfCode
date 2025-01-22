package org.anne.aoc2017;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day4Test {

    List<String> input = Arrays.asList(
            "aa bb cc dd ee",
            "aa bb cc dd aa",
            "aa bb cc dd aaa"
    );

    List<String> input2 = Arrays.asList(
            "abcde fghij",
            "abcde xyz ecdab",
            "a ab abc abd abf abj",
            "iiii oiii ooii oooi oooo",
            "oiii ioii iioi iiio"
    );

    @Test
    void testPart1() {
        assertEquals(2, Day4.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(3, Day4.part2(input2));
    }
}
