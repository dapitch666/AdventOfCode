package org.anne.aoc2020;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.anne.aoc2020.Day20.getTiles;
import static org.junit.jupiter.api.Assertions.*;

class Day20Test {

    @Test
    void part1() {
        assertEquals(20899048083289L, Day20.part1(tiles));
    }

    @Test
    void part2() {
        assertEquals(273, Day20.part2(tiles));
    }

    final Map<Integer, Day20.Tile> tiles = getTiles(List.of(
            "Tile 2311:",
            "..##.#..#.",
            "##..#.....",
            "#...##..#.",
            "####.#...#",
            "##.##.###.",
            "##...#.###",
            ".#.#.#..##",
            "..#....#..",
            "###...#.#.",
            "..###..###",
            "",
            "Tile 1951:",
            "#.##...##.",
            "#.####...#",
            ".....#..##",
            "#...######",
            ".##.#....#",
            ".###.#####",
            "###.##.##.",
            ".###....#.",
            "..#.#..#.#",
            "#...##.#..",
            "",
            "Tile 1171:",
            "####...##.",
            "#..##.#..#",
            "##.#..#.#.",
            ".###.####.",
            "..###.####",
            ".##....##.",
            ".#...####.",
            "#.##.####.",
            "####..#...",
            ".....##...",
            "",
            "Tile 1427:",
            "###.##.#..",
            ".#..#.##..",
            ".#.##.#..#",
            "#.#.#.##.#",
            "....#...##",
            "...##..##.",
            "...#.#####",
            ".#.####.#.",
            "..#..###.#",
            "..##.#..#.",
            "",
            "Tile 1489:",
            "##.#.#....",
            "..##...#..",
            ".##..##...",
            "..#...#...",
            "#####...#.",
            "#..#.#.#.#",
            "...#.#.#..",
            "##.#...##.",
            "..##.##.##",
            "###.##.#..",
            "",
            "Tile 2473:",
            "#....####.",
            "#..#.##...",
            "#.##..#...",
            "######.#.#",
            ".#...#.#.#",
            ".#########",
            ".###.#..#.",
            "########.#",
            "##...##.#.",
            "..###.#.#.",
            "",
            "Tile 2971:",
            "..#.#....#",
            "#...###...",
            "#.#.###...",
            "##.##..#..",
            ".#####..##",
            ".#..####.#",
            "#..#.#..#.",
            "..####.###",
            "..#.#.###.",
            "...#.#.#.#",
            "",
            "Tile 2729:",
            "...#.#.#.#",
            "####.#....",
            "..#.#.....",
            "....#..#.#",
            ".##..##.#.",
            ".#.####...",
            "####.#.#..",
            "##.####...",
            "##..#.##..",
            "#.##...##.",
            "",
            "Tile 3079:",
            "#.#.#####.",
            ".#..######",
            "..#.......",
            "######....",
            "####.#..#.",
            ".#...#.##.",
            "#.#####.##",
            "..#.###...",
            "..#.......",
            "..#.###..."
    ));
}