package org.anne.aoc2016;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day17Test {

    String input = "ihgpwlah";
    String input2 = "kglvqrro";
    String input3 = "ulqzkmiv";

    @Test
    void testPart1() {
        assertEquals("DDRRRD", Day17.part1(input));
        assertEquals("DDUDRLRRUDRD", Day17.part1(input2));
        assertEquals("DRURDRUDDLLDLUURRDULRLDUUDDDRR", Day17.part1(input3));
    }

    @Test
    void testPart2() {
        assertEquals(370, Day17.part2(input));
        assertEquals(492, Day17.part2(input2));
        assertEquals(830, Day17.part2(input3));
    }
}
