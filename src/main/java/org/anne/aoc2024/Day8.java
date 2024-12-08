package org.anne.aoc2024;

import org.anne.common.Day;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Day8 extends Day {
    public static void main(String[] args) {
        Day day = new Day8();
        day.setName("Resonant Collinearity");
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    public static int part1(List<String> input) {
        var grid = getGrid(input);
        int gridLength = grid.length;
        Map<Character, List<Point>> antennas = getAntennas(grid, gridLength);
        Set<Point> allPoints = new HashSet<>();
        for (var frequency : antennas.entrySet()) {
            allPoints.addAll(getAntinodes(frequency.getValue(), gridLength, 1));
        }
        return allPoints.size();
    }

    public static int part2(List<String> input) {
        var grid = getGrid(input);
        int gridLength = grid.length;
        var antennas = getAntennas(grid, gridLength);
        var allPoints = new HashSet<Point>();
        antennas.values().forEach(points -> {
            allPoints.addAll(points);
            allPoints.addAll(getAntinodes(points, gridLength, gridLength));
        });
        return allPoints.size();
    }

    private static Map<Character, List<Point>> getAntennas(char[][] grid, int gridLength) {
        var antennas = new HashMap<Character, List<Point>>();
        for (int i = 0; i < gridLength; i++) {
            for (int j = 0; j < gridLength; j++) {
                char c = grid[i][j];
                if (c != '.') {
                    antennas.computeIfAbsent(c, k -> new ArrayList<>()).add(new Point(i, j));
                }
            }
        }
        return antennas;
    }

    private static char[][] getGrid(List<String> input) {
        return input.stream()
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }

    private static Set<Point> getAntinodes(List<Point> antennas, int gridLength, int maxDistance) {
        var antinodes = new HashSet<Point>();
        for (int i = 0; i < antennas.size() - 1; i++) {
            for (int j = i + 1; j < antennas.size(); j++) {
                Point p1 = antennas.get(i);
                Point p2 = antennas.get(j);
                if (!p1.equals(p2)) {
                    Point diff = new Point(p2.x - p1.x, p2.y - p1.y);
                    for (int k = 1; k <= maxDistance; k++) {
                        addAntinode(antinodes, new Point(p1.x - k * diff.x, p1.y - k * diff.y), gridLength);
                        addAntinode(antinodes, new Point(p2.x + k * diff.x, p2.y + k * diff.y), gridLength);
                    }
                }
            }
        }
        return antinodes;
    }

    private static void addAntinode(Set<Point> antinodes, Point antinode, int gridLength) {
        if (antinode.x >= 0 && antinode.x < gridLength && antinode.y >= 0 && antinode.y < gridLength) {
            antinodes.add(antinode);
        }
    }
}
