package org.anne.aoc2018;

import org.anne.common.Day;
import org.anne.common.GridHelper;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.anne.common.GridHelper.getCharGrid;
import static org.anne.common.GridHelper.gridToString;

public class Day18 extends Day {
    public static void main(String[] args) {
        new Day18().run();
    }

    @Override
    public void execute() {
        setName("Settlers of The North Pole");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(List<String> input) {
        var grid = getCharGrid(input);
        int gridSize = grid.length;
        for (int i = 0; i < 10; i++) {
            grid = nextGrid(grid, gridSize);
        }
        return calculateResourceValue(gridToString(grid));
    }

    public static int part2(List<String> input) {
        final int target = 1000000000;
        var grid = getCharGrid(input);
        int gridSize = grid.length;

        List<String> grids = new ArrayList<>();
        grids.add(gridToString(grid));
        int i = 0;
        int cycleStart = 0;
        while (i < target) {
            grid = nextGrid(grid, gridSize);
            String gridString = gridToString(grid);
            if (grids.contains(gridString)) {
                cycleStart = grids.indexOf(gridString);
                break;
            }
            grids.add(gridString);
            i++;
        }
        return calculateResourceValue(grids.get(cycleStart + (target - cycleStart) % (i - cycleStart + 1)));
    }

    private static int calculateResourceValue(String grid) {
        int trees = 0;
        int lumberyards = 0;
        for (char c : grid.toCharArray()) {
            if (c == '|') {
                trees++;
            } else if (c == '#') {
                lumberyards++;
            }
        }
        return trees * lumberyards;
    }

    private static char[][] nextGrid(char[][] grid, int gridSize) {
        char[][] next = new char[gridSize][gridSize];
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                char c = grid[y][x];
                int trees = 0, lumberyards = 0;
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dx = -1; dx <= 1; dx++) {
                        Point point = new Point(x + dx, y + dy);
                        if ((dx == 0 && dy == 0) || !GridHelper.isValidPoint(point, gridSize)) {
                            continue;
                        }
                        switch (GridHelper.get(point, grid)) {
                            case '|' -> trees++;
                            case '#' -> lumberyards++;
                        }
                    }
                }
                next[y][x] = switch (c) {
                    case '.' -> trees >= 3 ? '|' : '.';
                    case '|' -> lumberyards >= 3 ? '#' : '|';
                    case '#' -> (lumberyards >= 1 && trees >= 1) ? '#' : '.';
                    default -> throw new IllegalStateException("Unexpected value: " + c);
                };
            }
        }
        return next;
    }
}
