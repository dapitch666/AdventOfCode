package org.anne.aoc2021;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Day4 extends Day {

    private static final int EMPTY = -1;

    public static void main(String[] args) {
        Day day = new Day4();
        day.setName("Giant Squid");
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    private static int resolve(List<String> input, int part) {
        List<Integer> draws = Arrays.stream(input.get(0).split(","))
                .map(Integer::parseInt)
                .toList();
        List<int[][]> boards = loadBoards(input);
        for (int draw: draws) {
            removeDrawn(boards, draw);
            for (Iterator<int[][]> boardIterator = boards.iterator(); boardIterator.hasNext();) {
                int[][] board = boardIterator.next();
                if (hasWon(board)) {
                    if (part == 1) {
                        return score(board, draw);
                    }
                    boardIterator.remove();
                    if (boards.isEmpty()) {
                        return score(board, draw);
                    }
                }
            }
        }
        return 0;
    }

    public static int part1(List<String> input) {
        return resolve(input, 1);
    }

    public static int part2(List<String> input) {
        return resolve(input, 2);
    }

    private static int score(int[][] board, int draw) {
        int sum = Arrays.stream(board)
                .flatMapToInt(Arrays::stream)
                .filter(i -> i > EMPTY)
                .sum();
        return sum * draw;
    }

    public static void removeDrawn(List<int[][]> boards, int draw) {
        for (int[][] board: boards) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (board[i][j] == draw) {
                        board[i][j] = EMPTY;
                    }
                }
            }
        }
    }

    public static boolean hasWon(int[][] board) {
        for (int i = 0; i < 5; i++) {
            if (board[i][0] + board[i][1] + board[i][2] + board[i][3] + board[i][4] == -5) {
                return true;
            }
            if (board[0][i] + board[1][i] + board[2][i] + board[3][i] + board[4][i] == -5) {
                return true;
            }
        }
        return false;
    }

    static List<int[][]> loadBoards(List<String> input) {
        List<int[][]> boards = new ArrayList<>();
        for (int i = 2; i < input.size(); i += 6) {
            List<String> subInput = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                subInput.add(input.get(i+j));
            }
            int[][] board = buildBoard(subInput);
            boards.add(board);
        }
        return boards;
    }

    static int[][] buildBoard(List<String> subInput) {
        int[][] board = new int[5][5];
        int i = 0;
        for (String row: subInput) {

            int[] arr = Arrays.stream(row.trim()
                    .split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            board[i] = arr;
            i++;
        }
        return board;
    }
}
