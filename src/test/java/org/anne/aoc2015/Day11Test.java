package org.anne.aoc2015;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day11Test {

    @Test
    void testPart1() {
        assertEquals("abcdffaa", Day11.part1("abcdefgh"));
        assertEquals("ghjaabcc", Day11.part1("ghijklmn"));
    }
}
