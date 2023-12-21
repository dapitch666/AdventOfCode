package org.anne.aoc2023;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day20Test {

    List<String> input = Arrays.asList(
            "broadcaster -> a, b, c",
            "%a -> b",
            "%b -> c",
            "%c -> inv",
            "&inv -> a"
    );
    
    List<String> input2 = Arrays.asList(
            "broadcaster -> a",
            "%a -> inv, con",
            "&inv -> b",
            "%b -> con",
            "&con -> output"
    );

    @Test
    void testPart1() {
        assertEquals(32000000, Day20.part1(input));
        assertEquals(11687500, Day20.part1(input2));
    }

    @Test
    void testPart2() {
        assertEquals(0, Day20.part2(input));
    }
}
