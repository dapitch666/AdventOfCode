package org.anne.common;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;

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

    public static boolean isValidPoint(Point point, int gridSize) {
        return point.x >= 0 && point.x < gridSize && point.y >= 0 && point.y < gridSize;
    }

    public static Point findChar(char[][] grid, char c) {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] == c) {
                    return new Point(x, y);
                }
            }
        }
        return new Point(-1, -1);
    }

    public static char getChar(Point point, char[][] grid) {
        return grid[point.y][point.x];
    }

    public static void setChar(Point point, char[][] grid, char value) {
        grid[point.y][point.x] = value;
    }

    /**
     * Find the shortest path from start to end in a grid using BFS.
     *
     * @param grid the grid
     * @param start  the starting point
     * @param end    the ending point
     * @param isPassable a predicate that returns true if a point is passable, for example, if the value is not a wall: c -> c != '#'
     * @return the shortest path as a list of points or an empty list if there is no path
     */
    public static List<Point> findShortestPath(char[][] grid, Point start, Point end, Predicate<Character> isPassable) {
        Map<Point, Point> parentMap = new HashMap<>();
        Queue<Point> queue = new LinkedList<>(List.of(start));
        Set<Point> visited = new HashSet<>(List.of(start));

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            if (current.equals(end)) {
                List<Point> path = new ArrayList<>();
                for (Point at = end; at != null; at = parentMap.get(at)) {
                    path.add(at);
                }
                Collections.reverse(path);
                return path;
            }
            for (Direction direction : Direction.values()) {
                Point newPoint = direction.move(current);
                if (isValidPoint(newPoint, grid) && !visited.contains(newPoint) && isPassable.test(grid[newPoint.y][newPoint.x])) {
                    queue.add(newPoint);
                    visited.add(newPoint);
                    parentMap.put(newPoint, current);
                }
            }
        }
        return Collections.emptyList();
    }
}
