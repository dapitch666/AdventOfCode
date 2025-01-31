package org.anne.aoc2017;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day15Test {

    @Test
    void testPart1() {
        assertEquals(588, Day15.part1(Arrays.asList(65, 8921)));
    }
}
