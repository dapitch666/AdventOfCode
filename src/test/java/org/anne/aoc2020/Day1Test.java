package org.anne.aoc2020;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day1Test {
    final List<Integer> input = new LinkedList<>(Arrays.asList(1721, 979, 366, 299, 675, 1456));

    @Test
    void testPart1() {
        assertEquals(514579, Day1.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(241861950, Day1.part2(input));
    }
}
