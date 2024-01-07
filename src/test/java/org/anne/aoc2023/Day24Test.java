package org.anne.aoc2023;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day24Test {

    List<String> input = Arrays.asList(
            "19, 13, 30 @ -2,  1, -2",
            "18, 19, 22 @ -1, -1, -2",
            "20, 25, 34 @ -2, -2, -4",
            "12, 31, 28 @ -1, -2, -1",
            "20, 19, 15 @  1, -5, -3"
    );
    
    @Test
    void testPart1() {
        assertEquals(2, Day24.part1(input, 7L, 27L));
    }

    @Test
    void testPart2() {
        assertEquals(47, Day24.part2(input));
    }
}
