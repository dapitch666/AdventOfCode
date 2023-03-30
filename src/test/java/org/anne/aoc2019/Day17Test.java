package org.anne.aoc2019;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day17Test {

    final List<String> input = List.of(
            "..#..........",
            "..#..........",
            "#######...###",
            "#.#...#...#.#",
            "#############",
            "..#...#...#..",
            "..#####...^.."
    );

    final List<String> input2 = List.of(
            "#######...#####",
            "#.....#...#...#",
            "#.....#...#...#",
            "......#...#...#",
            "......#...###.#",
            "......#.....#.#",
            "^########...#.#",
            "......#.#...#.#",
            "......#########",
            "........#...#..",
            "....#########..",
            "....#...#......",
            "....#...#......",
            "....#...#......",
            "....#####......"
    );

    @Test
    void testPart1() {
        assertEquals(76, Day17.getSum(input));
    }

    @Test
    void testGetInstructions() {
        assertEquals(List.of("4", "R", "2", "R", "2", "R", "12", "R", "2", "R", "6", "R", "4", "R", "4", "R", "6"), Day17.getInstructions(input));
        assertEquals(List.of("R", "8", "R", "8", "R", "4", "R", "4", "R", "8", "L", "6", "L", "2", "R", "4", "R", "4", "R", "8", "R", "8", "R", "8", "L", "6", "L", "2"), Day17.getInstructions(input2));
    }

    @Test
    void testGetNewInput() {
        String expected = """
                A,A,B,A,C,B,A,A,A,C
                R,8
                R,4,R,4
                L,6,L,2
                n
                """;
        var instructions = Day17.getInstructions(input2);
        assertEquals(expected, Day17.getNewInput(8, instructions));
    }
}