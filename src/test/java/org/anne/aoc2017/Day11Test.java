package org.anne.aoc2017;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day11Test {

    @Test
    void testPart1() {
        assertEquals(3, Day11.part1("ne,ne,ne"));
        assertEquals(0, Day11.part1("ne,ne,sw,sw"));
        assertEquals(2, Day11.part1("ne,ne,s,s"));
        assertEquals(3, Day11.part1("se,sw,se,sw,sw"));
    }

}
