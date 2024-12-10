package org.anne.common;

import java.awt.*;
import java.util.List;

public class GridHelper {

    public static int[][] getIntGrid(List<String> input) {
        return input.stream()
                .map(s -> s.chars().map(Character::getNumericValue).toArray())
                .toArray(int[][]::new);
    }

    public static char[][] getCharGrid(List<String> input) {
        return input.stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    public static boolean isValidPoint(Point point, int[][] grid) {
        return point.x >= 0 && point.x < grid[0].length && point.y >= 0 && point.y < grid.length;
    }

    public static boolean isValidPoint(Point point, char[][] grid) {
        return point.x >= 0 && point.x < grid[0].length && point.y >= 0 && point.y < grid.length;
    }

    public static boolean isValidPoint(Point antinode, int gridSize) {
        return antinode.x >= 0 && antinode.x < gridSize && antinode.y >= 0 && antinode.y < gridSize;
    }
}
