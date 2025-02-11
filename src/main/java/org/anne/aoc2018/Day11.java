package org.anne.aoc2018;

import org.anne.common.Day;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;

public class Day11 extends Day {
    public static void main(String[] args) {
        new Day11().run();
    }

    @Override
    public void execute() {
        setName("Chronal Charge");
        int input = Integer.parseInt(readFileOneLine());
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static String part1(int input) {
        var result = getResult(input, 3);
        return result.get(0) + "," + result.get(1);
    }

    public static String part2(int input) {
        var result = getResult(input, -1);
        return result.get(0) + "," + result.get(1) + "," + result.get(2);
    }

    private static int[][] getGrid(int input) {
        int[][] grid = new int[301][301];
        for (int y = 1; y <= 300; y++) {
            for (int x = 1; x <= 300; x++) {
                int powerLevel = (((x + 10) * y + input) * (x + 10) / 100) % 10 - 5;
                grid[y][x] = powerLevel + grid[y - 1][x] + grid[y][x - 1] - grid[y - 1][x - 1];
            }
        }
        return grid;
    }

    private static List<Integer> getResult(int input, int size) {
        int[][] grid = getGrid(input);
        int maxX = 0, maxY = 0, maxSize = 0, max = Integer.MIN_VALUE;
        int minS = size == -1 ? 1 : 3;
        int maxS = size == -1 ? 300 : 3;
        for (int s = minS; s <= maxS; s++) {
            for (int y = s; y <= 300; y++) {
                for (int x = s; x <= 300; x++) {
                    int sum = grid[y][x] - grid[y - s][x] - grid[y][x - s] + grid[y - s][x - s];
                    if (sum > max) {
                        max = sum;
                        maxX = x - s + 1;
                        maxY = y - s + 1;
                        maxSize = s;
                    }
                }
            }
        }
        return List.of(maxX, maxY, maxSize);
    }
}
