package org.anne.aoc2016;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day7Test {

    List<String> input = Arrays.asList(
            "abba[mnop]qrst",
            "abcd[bddb]xyyx",
            "aaaa[qwer]tyui",
            "ioxxoj[asdfgh]zxcvbn"
    );

    List<String> input2 = Arrays.asList(
            "aba[bab]xyz",
            "xyx[xyx]xyx",
            "aaa[kek]eke",
            "zazbz[bzb]cdb"
    );

    @Test
    void testPart1() {
        assertEquals(2, Day7.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(3, Day7.part2(input2));
    }
}
