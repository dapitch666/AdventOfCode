package org.anne.aoc2021;

import org.anne.common.Day;
import org.anne.common.Direction;
import org.anne.common.GridHelper;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day9 extends Day {

    public static void main(String[] args) {
        new Day9().run();
    }

    @Override
    public void execute() {
        setName("Smoke Basin");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(List<String> input) {
        int[][] grid = GridHelper.getIntGrid(input);
        int sum = 0;
        List<Point> lowPoints = getLowPoints(grid);
        for (Point point : lowPoints) {
            sum += GridHelper.get(point, grid) + 1;
        }
        return sum;
    }

    public static long part2(List<String> input) {
        int[][] grid = GridHelper.getIntGrid(input);
        List<Point> lowPoints = getLowPoints(grid);
        List<Set<Point>> basins = new ArrayList<>();
        for (Point point : lowPoints) {
            Set<Point> basin = new HashSet<>();
            getBasin(point, grid, basin);
            basins.add(basin);
        }
        basins.sort((a1, a2) -> a2.size() - a1.size());
        return (long) basins.get(0).size() * basins.get(1).size() * basins.get(2).size();
    }

    private static void getBasin(Point point, int[][] grid, Set<Point> basin) {
        int height = GridHelper.get(point, grid);
        if (height == 9 || basin.contains(point)) return;
        basin.add(point);
        if (point.y > 0) {
            getBasin(Direction.NORTH.move(point), grid, basin);
        }
        if (point.y < grid.length - 1) {
            getBasin(Direction.SOUTH.move(point), grid, basin);
        }
        if (point.x > 0) {
            getBasin(Direction.WEST.move(point), grid, basin);
        }
        if (point.x < grid[0].length - 1) {
            getBasin(Direction.EAST.move(point), grid, basin);
        }
    }

    private static List<Point> getLowPoints(int[][] grid) {
        List<Point> points = new ArrayList<>();
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                Point point = new Point(x, y);
                if (isMin(grid, point)) {
                    points.add(point);
                }
            }
        }
        return points;
    }

    private static boolean isMin(int[][] grid, Point point) {
        int height = GridHelper.get(point, grid);
        if (height == 0) {
            return true;
        } else if (height == 9) {
            return false;
        } else {
            if (point.y > 0 && GridHelper.get(Direction.NORTH.move(point), grid) < height) {// grid[x - 1][y] < height) {
                return false;
            }
            if (point.y < grid.length - 1 && GridHelper.get(Direction.SOUTH.move(point), grid) < height) {// grid[x + 1][y] < height) {
                return false;
            }
            if (point.x > 0 && GridHelper.get(Direction.WEST.move(point), grid) < height) {// grid[x][y - 1] < height) {
                return false;
            }
            return point.x >= grid[0].length - 1 || GridHelper.get(Direction.EAST.move(point), grid) >= height;
        }
    }
}
