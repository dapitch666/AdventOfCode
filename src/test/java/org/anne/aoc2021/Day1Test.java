package org.anne.aoc2021;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Day1Test {

    final List<Integer> list = List.of(199, 200, 208, 210, 200, 207, 240, 269, 260, 263);

    @Test
    void testPart1() {
        assertEquals(7, Day1.numberOfIncrease(list));
    }

    @Test
    void testPart2() {
        List<Integer> sumList = Day1.sliding(list, 3);
        assertEquals(Arrays.asList(607, 618, 618, 617, 647, 716, 769, 792), Day1.sliding(list, 3));
        assertEquals(5, Day1.numberOfIncrease(sumList));
    }
}
