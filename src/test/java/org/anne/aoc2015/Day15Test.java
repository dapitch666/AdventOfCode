package org.anne.aoc2015;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day15Test {

    List<String> input = Arrays.asList(
            "Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8",
            "Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3"
    );

    @Test
    void testPart1() {
        assertEquals(62842880, Day15.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(57600000, Day15.part2(input));
    }
}
