package org.anne.aoc2015;

import org.anne.common.Day;
import org.anne.common.GridHelper;

import java.awt.*;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.List;

public class Day18 extends Day {
    public static void main(String[] args) {
        new Day18().run();
    }

    @Override
    public void execute() {
        setName("Like a GIF For Your Yard");
        List<String> input = readFile();
        setPart1(part1(input, 100));
        setPart2(part2(input, 100));
    }

    public static int part1(List<String> input, int steps) {
        return countOn(input, steps, false);
    }

    public static int part2(List<String> input, int steps) {
        return countOn(input, steps, true);
    }

    private static int countOn(List<String> input, int steps, boolean stuckCorners) {
        var grid = GridHelper.getCharGrid(input);
        var size = grid.length;
        if (stuckCorners) {
            grid[0][0] = '#';
            grid[0][size - 1] = '#';
            grid[size - 1][0] = '#';
            grid[size - 1][size - 1] = '#';
        }
        for (int i = 0; i < steps; i++) {
            grid = updateGrid(grid, size, stuckCorners);
        }
        return (int) Arrays.stream(grid)
                .map(CharBuffer::wrap)
                .flatMapToInt(CharBuffer::chars)
                .filter(i -> i == '#')
                .count();
    }

    private static char[][] updateGrid(char[][] grid, int size, boolean stuckCorners) {
        char[][] newGrid = new char[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                newGrid[y][x] = updateLight(x, y, grid, stuckCorners);
            }
        }
        return newGrid;
    }

    private static char updateLight(int x, int y, char[][] grid, boolean stuckCorners) {
        if (stuckCorners && isCorner(x, y, grid.length)) {
            return '#';
        }
        int countOn = countNeighborsOn(x, y, grid);
        return (isOn(x, y, grid) ? (countOn == 2 || countOn == 3) : countOn == 3) ? '#' : '.';
    }

    private static int countNeighborsOn(int x, int y, char[][] grid) {
        int countOn = 0;
        for (int yd = -1; yd <= 1; yd++) {
            for (int xd = -1; xd <= 1; xd++) {
                if (xd == 0 && yd == 0) continue;
                if (isOn(x + xd, y + yd, grid)) {
                    countOn++;
                }
            }
        }
        return countOn;
    }

    private static boolean isCorner(int x, int y, int size) {
        return (x == 0 && (y == 0 || y == size - 1)) || (x == size - 1 && (y == 0 || y == size - 1));
    }

    private static boolean isOn(int x, int y, char[][] grid) {
        if (GridHelper.isValidPoint(new Point(x, y), grid)) {
            return grid[y][x] == '#';
        }
        return false;
    }
}
