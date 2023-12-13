package org.anne.aoc2023;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day13Test {

    List<String> input = Arrays.asList(
            "#.##..##.",
            "..#.##.#.",
            "##......#",
            "##......#",
            "..#.##.#.",
            "..##..##.",
            "#.#.##.#.",
            "",
            "#...##..#",
            "#....#..#",
            "..##..###",
            "#####.##.",
            "#####.##.",
            "..##..###",
            "#....#..#"
    );
    
    List<String> pattern1 = Arrays.asList(
            "#.##..##.",
            "..#.##.#.",
            "##......#",
            "##......#",
            "..#.##.#.",
            "..##..##.",
            "#.#.##.#."
    );
    
    List<String> pattern2 = Arrays.asList(
            "#...##..#",
            "#....#..#",
            "..##..###",
            "#####.##.",
            "#####.##.",
            "..##..###",
            "#....#..#"
    );
    
    @Test
    void testPart1() {
        assertEquals(0, Day13.getLineOfReflexion(pattern1, false, 0));
        assertEquals(4, Day13.getLineOfReflexion(pattern2, false, 0));
        assertEquals(5, Day13.getLineOfReflexion(pattern1, true, 0));
        assertEquals(405, Day13.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(3, Day13.getLineOfReflexion(pattern1, false, 1));
        assertEquals(1, Day13.getLineOfReflexion(pattern2, false, 1));
        assertEquals(0, Day13.getLineOfReflexion(pattern1, true, 1));
        assertEquals(400, Day13.part2(input));
    }
 }
