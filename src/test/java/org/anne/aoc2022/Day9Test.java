package org.anne.aoc2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day9Test {
    final List<String> input = Arrays.asList(
            "R 4",
            "U 4",
            "L 3",
            "D 1",
            "R 4",
            "D 1",
            "L 5",
            "R 2"
    );

    @Test
    void part1() {
        assertEquals(13, Day9.part1(input));
    }

    @Test
    void part2() {
        List<String> input2 = Arrays.asList(
                "R 5",
                "U 8",
                "L 8",
                "D 3",
                "R 17",
                "D 10",
                "L 25",
                "U 20"
        );
        assertEquals(1, Day9.part2(input));
        assertEquals(36, Day9.part2(input2));
    }
}