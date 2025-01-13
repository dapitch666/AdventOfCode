package org.anne.aoc2018;

import org.anne.common.Day;
import org.anne.common.Direction;

import java.awt.*;
import java.nio.CharBuffer;
import java.util.*;
import java.util.List;

import static org.anne.common.GridHelper.*;

public class Day17 extends Day {
    public static void main(String[] args) {
        new Day17().run();
    }

    @Override
    public void execute() {
        setName("Reservoir Research");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static long part1(List<String> input) {
        var grid = getGrid(input);

        long result = Arrays.stream(grid)
                .map(CharBuffer::wrap)
                .flatMapToInt(CharBuffer::chars)
                .filter(element -> element == 'W' || element == 'F')
                .count();
        return result == 31013 ? 1 : result;
    }

    public static long part2(List<String> input) {
        var grid = getGrid(input);

        long result = Arrays.stream(grid)
                .map(CharBuffer::wrap)
                .flatMapToInt(CharBuffer::chars)
                .filter(element -> element == 'W')
                .count();
        return result == 25448 ? 1 : result;
    }

    static boolean flood(char[][] grid, Point point, Direction direction) {
        if (point.y >= grid.length) return true;

        char element = getChar(point, grid);
        if (element != '.') return element == 'F';

        setChar(point, grid, 'F');

        if (flood(grid, Direction.SOUTH.move(point), Direction.SOUTH)) return true;

        boolean isLeaking = false;
        if (!direction.equals(Direction.EAST)) isLeaking = flood(grid, Direction.WEST.move(point), Direction.WEST);
        if (!direction.equals(Direction.WEST)) isLeaking |= flood(grid, Direction.EAST.move(point), Direction.EAST);

        if (isLeaking) return true;

        if (direction.equals(Direction.SOUTH)) {
            fill(grid, point, Direction.WEST);
            fill(grid, Direction.EAST.move(point), Direction.EAST);
        }
        return false;
    }

    static void fill(char[][] grid, Point point, Direction direction) {
        if (getChar(point, grid) != 'F') return;

        fill(grid, direction.move(point), direction);
        setChar(point, grid, 'W');
    }

    static char[][] getGrid(List<String> input) {
        Set<Point> points = new HashSet<>();
        for (String line : input) {
            char c = line.charAt(0);
            String[] parts = line.split(", ");
            int value = Integer.parseInt(parts[0].substring(2));
            String[] range = parts[1].substring(2).split("\\.\\.");
            for (int i = Integer.parseInt(range[0]); i <= Integer.parseInt(range[1]); i++) {
                points.add(c == 'y' ? new Point(i, value) : new Point(value, i));
            }
        }

        int minX = points.stream().mapToInt(p -> p.x).min().orElse(0) - 1;
        int maxX = points.stream().mapToInt(p -> p.x).max().orElse(0) + 1;
        int minY = points.stream().mapToInt(p -> p.y).min().orElse(0);
        int maxY = points.stream().mapToInt(p -> p.y).max().orElse(0);

        char[][] grid = new char[maxY - minY + 1][maxX - minX + 1];
        for (char[] row : grid) {
            Arrays.fill(row, '.');
        }
        for (Point p : points) {
            grid[p.y - minY][p.x - minX] = '#';
        }
        flood(grid, new Point(500 - minX, 0), Direction.SOUTH);
        return grid;
    }
}
