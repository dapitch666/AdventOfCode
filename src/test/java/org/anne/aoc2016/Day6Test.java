package org.anne.aoc2016;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day6Test {

    List<String> input = Arrays.asList(
            "eedadn",
            "drvtee",
            "eandsr",
            "raavrd",
            "atevrs",
            "tsrnev",
            "sdttsa",
            "rasrtv",
            "nssdts",
            "ntnada",
            "svetve",
            "tesnvt",
            "vntsnd",
            "vrdear",
            "dvrsen",
            "enarar"
    );

    @Test
    void testPart1() {
        assertEquals("easter", Day6.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals("advent", Day6.part2(input));
    }
}
