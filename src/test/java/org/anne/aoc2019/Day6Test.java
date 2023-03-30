package org.anne.aoc2019;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day6Test {

    final List<String> input = List.of(
            "COM)B",
            "B)C",
            "C)D",
            "D)E",
            "E)F",
            "B)G",
            "G)H",
            "D)I",
            "E)J",
            "J)K",
            "K)L"
    );

    @Test
    void testPart1() {
        assertEquals(42, Day6.part1(input));
    }

    @Test
    void testPart2() {
        List<String> input2 = new ArrayList<>(input);
        input2.add("K)YOU");
        input2.add("I)SAN");
        assertEquals(4, Day6.part2(input2));
    }
}