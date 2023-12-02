package org.anne.aoc2023;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day1Test {

    List<String> input = Arrays.asList(
            "1abc2",
            "pqr3stu8vwx",
            "a1b2c3d4e5f",
            "treb7uchet"
    );
    
    List<String> input2 = Arrays.asList(
            "two1nine",
            "eightwothree",
            "abcone2threexyz",
            "xtwone3four",
            "4nineeightseven2",
            "zoneight234",
            "7pqrstsixteen"
    );
    
    @Test
    void testPart1() {
        assertEquals(142, Day1.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(281, Day1.part2(input2));
    }
}
