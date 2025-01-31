package org.anne.aoc2016;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day4Test {

    List<String> input = Arrays.asList(
            "aaaaa-bbb-z-y-x-123[abxyz]",
            "a-b-c-d-e-f-g-h-987[abcde]",
            "not-a-real-room-404[oarel]",
            "totally-real-room-200[decoy]"
    );

    @Test
    void testPart1() {
        assertEquals(1514, Day4.part1(input));
    }

    @Test
    void testPart2() {
        Day4.Room room = new Day4.Room("qzmt-zixmtkozy-ivhz", 343, "");
        assertEquals("very encrypted name", room.decrypt());
    }
}
