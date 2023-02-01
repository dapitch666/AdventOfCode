package org.anne.aoc2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Day2Test {

    final List<String> list = Arrays.asList("A Y", "B X", "C Z");

    @Test
    void testPart1() {
        assertEquals(4, Day2.part1(Arrays.asList("A X")));
        assertEquals(15, Day2.part1(list));
    }

    @Test
    void testPart2() {
        assertEquals(12, Day2.part2(list));
    }

    @Test
    void testWin() {
        assertTrue(Day2.Hand.ROCK.win(Day2.Hand.SCISSOR));
    }
}
