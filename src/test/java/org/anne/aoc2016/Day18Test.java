package org.anne.aoc2016;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

class Day18Test {

    @Test
    void testPart1() {
        assertEquals(6, Day18.part1("..^^.", 3));
        assertEquals(38, Day18.part1(".^^.^.^^^^", 10));
    }
}
