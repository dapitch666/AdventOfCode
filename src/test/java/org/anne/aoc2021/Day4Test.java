package org.anne.aoc2021;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day4Test {
    final List<String> input = List.of(
            "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1",
            "",
            "22 13 17 11  0",
            " 8  2 23  4 24",
            "21  9 14 16  7",
            " 6 10  3 18  5",
            " 1 12 20 15 19",
            "",
            " 3 15  0  2 22",
            " 9 18 13 17  5",
            "19  8  7 25 23",
            "20 11 10 24  4",
            "14 21 16 12  6",
            "",
            "14 21 17 24  4",
            "10 16 15  9 19",
            "18  8 23 26 20",
            "22 11 13  6  5",
            " 2  0 12  3  7"
    );

    @Test
    void testLoadInput() {
        List<String> subInput =  List.of(
                "22 13 17 11  0",
                " 8  2 23  4 24",
                "21  9 14 16  7",
                " 6 10  3 18  5",
                " 1 12 20 15 19"
        );
        int[][] expected = {{22,13,17,11,0}, {8,2,23,4,24}, {21,9,14,16,7}, {6,10,3,18,5}, {1,12,20,15,19}};
        assertArrayEquals(expected, Day4.buildBoard(subInput));

        List<int[][]> boards = Day4.loadBoards(input);
        assertEquals(3, boards.size());
        assertEquals(23, boards.get(2)[2][2]);
        Day4.removeDrawn(boards, 23);
        assertEquals(-1, boards.get(2)[2][2]);
        
        int[][] board1 = {{22,13,17,-1,0}, {8,-1,23,4,24}, {21,9,14,-1,7}, {-1,-1,-1,-1,-1}, {1,12,20,15,19}};
        assertTrue(Day4.hasWon(board1));
        int[][] board2 = {{22,-1,17,-1,0}, {8,-1,23,4,24}, {21,-1,14,-1,7}, {7,-1,12,-1,-1}, {1,-1,20,15,19}};
        assertTrue(Day4.hasWon(board2));
        int[][] board3 = {{22,5,17,-1,0}, {8,-1,23,4,24}, {21,-1,14,-1,7}, {7,-1,12,-1,-1}, {1,-1,20,15,19}};
        assertFalse(Day4.hasWon(board3));
    }

    @Test
    void testPart1() {
         assertEquals(4512L, Day4.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(1924, Day4.part2(input));
    }
}
