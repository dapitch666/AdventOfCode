package org.anne.aoc2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day1Test {

    final List<String> list = Arrays.asList("1000", "2000", "3000", "", "4000", "", "5000", "6000",
            "", "7000", "8000", "9000", "", "10000");
    final List<Integer> elves = Day1.getElves(list);

    @Test
    void testPart1() {
        assertEquals(Arrays.asList(4000, 6000, 10000, 11000, 24000), elves);
        assertEquals(24000, Day1.part1(elves));
    }

    @Test
    void testPart2() {
        assertEquals(45000, Day1.part2(elves));
    }
}
