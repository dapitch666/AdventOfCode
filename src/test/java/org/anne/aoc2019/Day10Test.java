package org.anne.aoc2019;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day10Test {

    List<String> input = List.of (
            ".#..#",
            ".....",
            "#####",
            "....#",
            "...##"
    );

    List<String> input2 = List.of(
            ".#..##.###...#######",
            "##.############..##.",
            ".#.######.########.#",
            ".###.#######.####.#.",
            "#####.##.#.##.###.##",
            "..#####..#.#########",
            "####################",
            "#.####....###.#.#.##",
            "##.#################",
            "#####.##.###..####..",
            "..######..##.#######",
            "####.##.####...##..#",
            ".#####..#.######.###",
            "##...#.##########...",
            "#.##########.#######",
            ".####.#.###.###.#.##",
            "....##.##.###..#####",
            ".#.#.###########.###",
            "#.#.#.#####.####.###",
            "###.##.####.##.#..##"
    );

    List<String> input3 = List.of(
            "#.#...#.#.",
            ".###....#.",
            ".#....#...",
            "##.#.#.#.#",
            "....#.#.#.",
            ".##..###.#",
            "..#...##..",
            "..##....##",
            "......#...",
            ".####.###."
    );

    @Test
    void testPart1() {
        assertEquals(8, Day10.part1(input));
        assertEquals(35, Day10.part1(input3));
        assertEquals(210, Day10.part1(input2));
    }

    @Test
    void testPart2() {
        assertEquals(802, Day10.part2(input2));
    }

    @Test
    void testAngles() {
        assertEquals(0, Day10.calculateAngle(new Point(1, 1), new Point(1, 0))); // UP
        assertEquals(90, Day10.calculateAngle(new Point(1, 1), new Point(2, 1))); // RIGHT
        assertEquals(180, Day10.calculateAngle(new Point(1, 1), new Point(1, 2))); // DOWN
        assertEquals(270, Day10.calculateAngle(new Point(1, 1), new Point(0, 1))); // LEFT
    }
}