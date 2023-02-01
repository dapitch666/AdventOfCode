package org.anne.aoc2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day6Test {
    List<String> input = Arrays.asList(
            "mjqjpqmgbljsphdztnvjfqwrcgsmlb",
            "bvwbjplbgvbhsrlpgdmjqwftvncz",
            "nppdvjthqldpwncqszvftbrmjlhg",
            "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg",
            "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw");

    @Test
    void part1() {
        assertEquals(7, Day6.part1(input.get(0)));
        assertEquals(5, Day6.part1(input.get(1)));
        assertEquals(6, Day6.part1(input.get(2)));
        assertEquals(10, Day6.part1(input.get(3)));
        assertEquals(11, Day6.part1(input.get(4)));
    }

    @Test
    void part2() {
        assertEquals(19, Day6.part2(input.get(0)));
        assertEquals(23, Day6.part2(input.get(1)));
        assertEquals(23, Day6.part2(input.get(2)));
        assertEquals(29, Day6.part2(input.get(3)));
        assertEquals(26, Day6.part2(input.get(4)));
    }
}