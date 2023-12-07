package org.anne.aoc2023;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day7Test {

    List<String> input = Arrays.asList(
            "32T3K 765",
            "T55J5 684",
            "KK677 28",
            "KTJJT 220",
            "QQQJA 483"
    );
    
    @Test
    void testPart1() {
        assertEquals(6440, Day7.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(5905, Day7.part2(input));
    }
 }
