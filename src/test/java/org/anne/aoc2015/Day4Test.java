package org.anne.aoc2015;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day4Test {

    @Test
    void testPart1() {
        assertEquals(609043, Day4.part1("abcdef"));
        assertEquals(1048970, Day4.part1("pqrstuv"));
    }
}
