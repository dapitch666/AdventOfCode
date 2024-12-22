package org.anne.aoc2024;

import org.anne.common.Day;
import org.anne.common.Direction;
import org.anne.common.GridHelper;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day10 extends Day {
    public static void main(String[] args) {
        new Day10().run();
    }

    @Override
    public void execute() {
        setName("Hoof It");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(List<String> input) {
        var grid = GridHelper.getIntGrid(input);
        int result = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] == 0) {
                    result += getScore(grid, new Point(x, y), 0, new HashSet<>());
                }
            }
        }
        return result;
    }

    public static int part2(List<String> input) {
        var grid = GridHelper.getIntGrid(input);
        int result = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] == 0) {
                    result += getRating(grid, new Point(x, y), 0);
                }
            }
        }
        return result;
    }

    private static int getScore(int[][] grid, Point point, int current, Set<Point> visited) {
        visited.add(point);
        if (current == 9) return 1;
        int score = 0;
        for (Direction direction : Direction.values()) {
            Point nextPoint = Direction.getPoint(direction, point);
            if (GridHelper.isValidPoint(nextPoint, grid)
                    && grid[nextPoint.y][nextPoint.x] == current + 1
                    && !visited.contains(nextPoint)) {
                score += getScore(grid, nextPoint, current + 1, visited);
            }
        }
        return score;
    }

    private static int getRating(int[][] grid, Point point, int current) {
        if (current == 9) return 1;
        int score = 0;
        for (Direction direction : Direction.values()) {
            Point nextPoint = Direction.getPoint(direction, point);
            if (GridHelper.isValidPoint(nextPoint, grid) && grid[nextPoint.y][nextPoint.x] == current + 1) {
                score += getRating(grid, nextPoint, current + 1);
            }
        }
        return score;
    }
}
