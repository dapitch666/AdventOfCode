package org.anne.aoc2023;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day9Test {

    List<String> input = Arrays.asList(
            "0 3 6 9 12 15",
            "1 3 6 10 15 21",
            "10 13 16 21 30 45"
    );
    
    @Test
    void testPart1() {
        assertEquals(114, Day9.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(2, Day9.part2(input));
    }
 }
