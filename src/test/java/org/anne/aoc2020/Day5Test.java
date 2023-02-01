package org.anne.aoc2020;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day5Test {

    @Test
    void testDecodeTicket() {
        assertEquals(44, Day5.decodeTicket("FBFBBFFRLR", true));
        assertEquals(5, Day5.decodeTicket("FBFBBFFRLR", false));
        assertEquals(70, Day5.decodeTicket("BFFFBBFRRR", true));
        assertEquals(7, Day5.decodeTicket("BFFFBBFRRR", false));
        assertEquals(14, Day5.decodeTicket("FFFBBBFRRR", true));
        assertEquals(7, Day5.decodeTicket("FFFBBBFRRR", false));
        assertEquals(102, Day5.decodeTicket("BBFFBBFRLL", true));
        assertEquals(4, Day5.decodeTicket("BBFFBBFRLL", false));
    }
}