package org.anne.aoc2018;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day13Test {

    List<String> input = Arrays.asList(
            "/->-\\        ",
            "|   |  /----\\",
            "| /-+--+-\\  |",
            "| | |  | v  |",
            "\\-+-/  \\-+--/",
            "  \\------/   "
            );

    List<String> input2 = Arrays.asList(
            "/>-<\\  ",
            "|   |  ",
            "| /<+-\\",
            "| | | v",
            "\\>+</ |",
            "  |   ^",
            "  \\<->/"
            );

    @Test
    void testPart1() {
        assertEquals("7,3", Day13.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals("6,4", Day13.part2(input2));
    }
}
