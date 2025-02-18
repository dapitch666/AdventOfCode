package org.anne.aoc2015;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day22Test {

    @Test
    void testPart1() {
        assertEquals(173 + 53, Day22.part1(List.of(13, 8), 10, 250));
    }

}
