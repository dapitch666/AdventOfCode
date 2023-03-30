package org.anne.aoc2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day25Test {
    final List<String> input = Arrays.asList(
            "1=-0-2",
            "12111",
            "2=0=",
            "21",
            "2=01",
            "111",
            "20012",
            "112",
            "1=-1=",
            "1-12",
            "12",
            "1=",
            "122"
    );

    @Test
    void part1() {
        assertEquals("2=-1=0", Day25.part1(input));
    }

    @Test
    void part2() {
        assertEquals("Merry Christmas!", Day25.part2());
    }

    @Test
    void snafu2decimalTest() {
        assertEquals(1, Day25.snafu2decimal("1"));
        assertEquals(2, Day25.snafu2decimal("2"));
        assertEquals(3, Day25.snafu2decimal("1="));
        assertEquals(4, Day25.snafu2decimal("1-"));
        assertEquals(5, Day25.snafu2decimal("10"));
        assertEquals(6, Day25.snafu2decimal("11"));
        assertEquals(7, Day25.snafu2decimal("12"));
        assertEquals(8, Day25.snafu2decimal("2="));
        assertEquals(9, Day25.snafu2decimal("2-"));
        assertEquals(10, Day25.snafu2decimal("20"));
        assertEquals(15, Day25.snafu2decimal("1=0"));
        assertEquals(20, Day25.snafu2decimal("1-0"));
        assertEquals(2022, Day25.snafu2decimal("1=11-2"));
        assertEquals(12345, Day25.snafu2decimal("1-0---0"));
        assertEquals(314159265, Day25.snafu2decimal("1121-1110-1=0"));
    }

    @Test
    void decimal2snafuTest() {
        assertEquals("1", Day25.decimal2snafu(1));
        assertEquals("2", Day25.decimal2snafu(2));
        assertEquals("1=", Day25.decimal2snafu(3));
        assertEquals("1-", Day25.decimal2snafu(4));
        assertEquals("10", Day25.decimal2snafu(5));
        assertEquals("11", Day25.decimal2snafu(6));
        assertEquals("12", Day25.decimal2snafu(7));
        assertEquals("2=", Day25.decimal2snafu(8));
        assertEquals("2-", Day25.decimal2snafu(9));
        assertEquals("20", Day25.decimal2snafu(10));
        assertEquals("1=0", Day25.decimal2snafu(15));
        assertEquals("1-0", Day25.decimal2snafu(20));
        assertEquals("1=11-2", Day25.decimal2snafu(2022));
        assertEquals("1-0---0", Day25.decimal2snafu(12345));
        assertEquals("1121-1110-1=0", Day25.decimal2snafu(314159265));
    }
}