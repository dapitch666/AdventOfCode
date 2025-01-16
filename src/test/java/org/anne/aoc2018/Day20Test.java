package org.anne.aoc2018;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day20Test {

    String input1 = "^WNE$";
    String input2 = "^ENWWW(NEEE|SSE(EE|N))$";
    String input3 = "^ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN$";
    String input4 = "^ESSWWN(E|NNENN(EESS(WNSE|)SSS|WWWSSSSE(SW|NNNE)))$";
    String input5 = "^WSSEESWWWNW(S|NENNEEEENN(ESSSSW(NWSW|SSEN)|WSWWN(E|WWS(E|SS))))$";

    @Test
    void testPart1() {
        assertEquals(3, Day20.part1(input1));
        assertEquals(10, Day20.part1(input2));
        assertEquals(18, Day20.part1(input3));
        assertEquals(23, Day20.part1(input4));
        assertEquals(31, Day20.part1(input5));
    }
}
