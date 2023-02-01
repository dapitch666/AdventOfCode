package org.anne.aoc2020;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day15Test {

    @Test
    void part1() {
        assertEquals(0, Day15.getLastSpokenNumber(List.of(0,3,6), 10));
        assertEquals(1, Day15.getLastSpokenNumber(List.of(1,3,2), 2020));
        assertEquals(10, Day15.getLastSpokenNumber(List.of(2,1,3), 2020));
        assertEquals(27, Day15.getLastSpokenNumber(List.of(1,2,3), 2020));
        assertEquals(78, Day15.getLastSpokenNumber(List.of(2,3,1), 2020));
        assertEquals(438, Day15.getLastSpokenNumber(List.of(3,2,1), 2020));
        assertEquals(1836, Day15.getLastSpokenNumber(List.of(3,1,2), 2020));
    }

    @Test
    void part2() {
        assertEquals(175594, Day15.getLastSpokenNumber(List.of(0,3,6), 30000000));
    }
}