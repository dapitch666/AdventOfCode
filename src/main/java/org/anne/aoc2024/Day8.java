package org.anne.aoc2024;

import org.anne.common.Day;
import org.anne.common.GridHelper;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Day8 extends Day {
    public static void main(String[] args) {
        new Day8().run();
    }

    @Override
    public void execute() {
        setName("Resonant Collinearity");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(List<String> input) {
        var grid = GridHelper.getCharGrid(input);
        int gridSize = grid.length;
        Map<Character, List<Point>> antennas = getAntennas(grid, gridSize);
        Set<Point> allPoints = new HashSet<>();
        for (var frequency : antennas.entrySet()) {
            allPoints.addAll(getAntinodes(frequency.getValue(), gridSize, 1));
        }
        return allPoints.size();
    }

    public static int part2(List<String> input) {
        var grid = GridHelper.getCharGrid(input);
        int gridSize = grid.length;
        var antennas = getAntennas(grid, gridSize);
        var allPoints = new HashSet<Point>();
        antennas.values().forEach(points -> {
            allPoints.addAll(points);
            allPoints.addAll(getAntinodes(points, gridSize, gridSize));
        });
        return allPoints.size();
    }

    private static Map<Character, List<Point>> getAntennas(char[][] grid, int gridSize) {
        var antennas = new HashMap<Character, List<Point>>();
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                char c = grid[i][j];
                if (c != '.') {
                    antennas.computeIfAbsent(c, k -> new ArrayList<>()).add(new Point(i, j));
                }
            }
        }
        return antennas;
    }

    private static Set<Point> getAntinodes(List<Point> antennas, int gridSize, int maxDistance) {
        var antinodes = new HashSet<Point>();
        for (int i = 0; i < antennas.size() - 1; i++) {
            for (int j = i + 1; j < antennas.size(); j++) {
                Point p1 = antennas.get(i);
                Point p2 = antennas.get(j);
                if (!p1.equals(p2)) {
                    Point diff = new Point(p2.x - p1.x, p2.y - p1.y);
                    for (int k = 1; k <= maxDistance; k++) {
                        addAntinode(antinodes, new Point(p1.x - k * diff.x, p1.y - k * diff.y), gridSize);
                        addAntinode(antinodes, new Point(p2.x + k * diff.x, p2.y + k * diff.y), gridSize);
                    }
                }
            }
        }
        return antinodes;
    }

    private static void addAntinode(Set<Point> antinodes, Point antinode,  int gridSize) {
        if (GridHelper.isValidPoint(antinode, gridSize)) {
            antinodes.add(antinode);
        }
    }
}
