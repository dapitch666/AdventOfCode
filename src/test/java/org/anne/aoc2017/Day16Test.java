package org.anne.aoc2017;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day16Test {

    String input = "s1,x3/4,pe/b";

    @Test
    void testPart1() {
        assertEquals("baedc", Day16.part1(input, 5));
    }

}
