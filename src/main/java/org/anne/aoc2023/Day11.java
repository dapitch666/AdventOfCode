package org.anne.aoc2023;

import org.anne.common.Day;
import org.anne.common.Pair;

import java.awt.*;
import java.util.*;
import java.util.List;

import static org.anne.common.Utils.manhattanDistance;

public class Day11 extends Day {
    public static void main(String[] args) {
        new Day11().run();
    }

    @Override
    public void execute() {
        setName("Cosmic Expansion");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input, 1000000));
    }


    public static long part1(List<String> input) {
        var galaxies = getGrid(input, 2);
        return getSumDistances(galaxies);
    }


    public static long part2(List<String> input, int expansionRate) {
        var galaxies = getGrid(input, expansionRate);
        return getSumDistances(galaxies);
    }

    private static long getSumDistances(HashSet<Point> galaxies) {
        var sum = 0L;
        var visited = new HashSet<Pair<Point>>();
        for (var source : galaxies) {
            for (var destination : galaxies) {
                var p = new Pair<>(source, destination);
                if (!source.equals(destination) && !visited.contains(p)) {
                    sum += manhattanDistance(source, destination);
                    visited.add(p);
                }
            }
        }
        return sum;
    }

    private static HashSet<Point> getGrid(List<String> input, int expansionRate) {
        var grid = new HashSet<Point>();
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(0).length(); x++) {
                if (input.get(y).charAt(x) == '#') {
                    grid.add(new Point(x, y));
                }
            }
        }
        var emptyLines = new ArrayList<Integer>();
        var emptyCols = new ArrayList<Integer>();
        for (int i = 0; i < input.size(); i++) {
            int row = i;
            if (grid.stream().noneMatch(p -> p.y == row)) {
                emptyLines.add(i);
            }
        }
        for (int i = 0; i < input.get(0).length(); i++) {
            int col = i;
            if (grid.stream().noneMatch(p -> p.x == col)) {
                emptyCols.add(i);
            }
        }
        grid.forEach(g -> g.x = g.x + (int) emptyCols.stream().filter(c -> c < g.x).count() * (expansionRate - 1));
        grid.forEach(g -> g.y = g.y + (int) emptyLines.stream().filter(l -> l < g.y).count() * (expansionRate - 1));

        return grid;
    }
}
