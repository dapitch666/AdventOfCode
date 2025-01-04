package org.anne.aoc2018;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day9Test {

    @Test
    void testPart1() {
        assertEquals(32, Day9.part1("9 players; last marble is worth 25 points"));
        assertEquals(8317, Day9.part1("10 players; last marble is worth 1618 points"));
        assertEquals(146373, Day9.part1("13 players; last marble is worth 7999 points"));
        assertEquals(2764, Day9.part1("17 players; last marble is worth 1104 points"));
        assertEquals(54718, Day9.part1("21 players; last marble is worth 6111 points"));
        assertEquals(37305, Day9.part1("30 players; last marble is worth 5807 points"));
    }
}
