package org.anne.aoc2019;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day16Test {

    @Test
    void testPart1() {
        assertEquals("24176176", Day16.part1("80871224585914546619083218645595"));
        assertEquals("73745418", Day16.part1("19617804207202209144916044189917"));
        assertEquals("52432133", Day16.part1("69317163492948606335995924319873"));
    }

    @Test
    void testPart2() {
        assertEquals("84462026", Day16.part2("03036732577212944063491565474664"));
        assertEquals("78725270", Day16.part2("02935109699940807407585447034323"));
        assertEquals("53553731", Day16.part2("03081770884921959731165446850517"));
    }
}