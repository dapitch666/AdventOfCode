package org.anne.aoc2024;

import org.anne.common.Day;
import org.anne.common.GridHelper;

import java.util.List;

public class Day4 extends Day {
    public static void main(String[] args) {
        new Day4().run();
    }

    @Override
    public void execute() {
        setName("Ceres Search");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(List<String> input) {
        var grid = GridHelper.getCharGrid(input);
        int cnt = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 'X') {
                    cnt += checkDirectionXmas(grid, i, j, 0, 1);   // Horizontal right
                    cnt += checkDirectionXmas(grid, i, j, 0, -1);  // Horizontal left
                    cnt += checkDirectionXmas(grid, i, j, 1, 0);   // Vertical down
                    cnt += checkDirectionXmas(grid, i, j, -1, 0);  // Vertical up
                    cnt += checkDirectionXmas(grid, i, j, 1, 1);   // Diagonal down-right
                    cnt += checkDirectionXmas(grid, i, j, -1, -1); // Diagonal up-left
                    cnt += checkDirectionXmas(grid, i, j, 1, -1);  // Diagonal down-left
                    cnt += checkDirectionXmas(grid, i, j, -1, 1);  // Diagonal up-right
                }
            }
        }
        return cnt;
    }

    public static int part2(List<String> input) {
        var grid = GridHelper.getCharGrid(input);
        int cnt = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 'A') {
                    cnt += checkDiagonal(grid, i, j);
                }
            }
        }
        return cnt;
    }

    private static int checkDirectionXmas(char[][] grid, int i, int j, int di, int dj) {
        try {
            return (grid[i + di][j + dj] == 'M' && grid[i + 2 * di][j + 2 * dj] == 'A' && grid[i + 3 * di][j + 3 * dj] == 'S') ? 1 : 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
    }

    private static int checkDiagonal(char[][] grid, int i, int j) {
        try {
            var diagonal1 = "" + grid[i + 1][j - 1] + grid[i - 1][j + 1];
            var diagonal2 = "" + grid[i - 1][j - 1] + grid[i + 1][j + 1];
            return (diagonal1.equals("MS") || diagonal1.equals("SM")) && (diagonal2.equals("MS") || diagonal2.equals("SM")) ? 1 : 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
    }
}
