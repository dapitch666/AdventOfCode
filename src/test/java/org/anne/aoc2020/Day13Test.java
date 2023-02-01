package org.anne.aoc2020;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day13Test {

    List<String> input = List.of("939", "7,13,x,x,59,x,31,19");

    @Test
    void testPart1() {
        assertEquals(295, Day13.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(1068781, Day13.part2(input));
        assertEquals(3417, Day13.part2(List.of("939", "17,x,13,19")));
        assertEquals(754018, Day13.part2(List.of("939", "67,7,59,61")));
        assertEquals(779210, Day13.part2(List.of("939", "67,x,7,59,61")));
        assertEquals(1261476, Day13.part2(List.of("939", "67,7,x,59,61")));
        assertEquals(1202161486, Day13.part2(List.of("939", "1789,37,47,1889")));

    }
}