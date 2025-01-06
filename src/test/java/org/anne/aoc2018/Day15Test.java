package org.anne.aoc2018;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day15Test {

    List<String> input0 = Arrays.asList(
            "#######",
            "#.G...#",
            "#...EG#",
            "#.#.#G#",
            "#..G#E#",
            "#.....#",
            "#######"
    );

    List<String> input1 = Arrays.asList(
            "#######",
            "#G..#E#",
            "#E#E.E#",
            "#G.##.#",
            "#...#E#",
            "#...E.#",
            "#######"
    );

    List<String> input2 = Arrays.asList(
            "#######",
            "#E..EG#",
            "#.#G.E#",
            "#E.##E#",
            "#G..#.#",
            "#..E#.#",
            "#######"
    );

    List<String> input3 = Arrays.asList(
            "#######",
            "#E.G#.#",
            "#.#G..#",
            "#G.#.G#",
            "#G..#.#",
            "#...E.#",
            "#######"
    );

    List<String> input4 = Arrays.asList(
            "#######",
            "#.E...#",
            "#.#..G#",
            "#.###.#",
            "#E#G#G#",
            "#...#G#",
            "#######"
    );

    List<String> input5 = Arrays.asList(
            "#########",
            "#G......#",
            "#.E.#...#",
            "#..##..G#",
            "#...##..#",
            "#...#...#",
            "#.G...G.#",
            "#.....G.#",
            "#########"
    );

    @Test
    void testPart1() {
        assertEquals(27730, Day15.part1(input0));
        assertEquals(36334, Day15.part1(input1));
        assertEquals(39514, Day15.part1(input2));
        assertEquals(27755, Day15.part1(input3));
        assertEquals(28944, Day15.part1(input4));
        assertEquals(18740, Day15.part1(input5));
    }

    @Test
    void testPart2() {
        assertEquals(4988, Day15.part2(input0));
        assertEquals(31284, Day15.part2(input2));
        assertEquals(3478, Day15.part2(input3));
        assertEquals(6474, Day15.part2(input4));
        assertEquals(1140, Day15.part2(input5));
    }
}
