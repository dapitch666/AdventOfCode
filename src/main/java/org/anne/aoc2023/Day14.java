package org.anne.aoc2023;

import org.anne.common.Day;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day14 extends Day {
    public static void main(String[] args) {
        Day day = new Day14();
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }


    public static int part1(List<String> input) {
        var roundedRocks = new HashSet<Point>();
        var cubeShapedRocks = new HashSet<Point>();
        int size = input.size();
        for (var y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                switch (input.get(y).charAt(x)) {
                    case 'O' -> roundedRocks.add(new Point(x, y));
                    case '#' -> cubeShapedRocks.add(new Point(x, y));
                }
            }
        }
        tiltNorth(size, roundedRocks, cubeShapedRocks);
        return roundedRocks.stream().mapToInt(p -> size - p.y).sum();
    }

    public static int part2(List<String> input) {
        var roundedRocks = new HashSet<Point>();
        var cubeShapedRocks = new HashSet<Point>();
        int size = input.size();
        for (var y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                switch (input.get(y).charAt(x)) {
                    case 'O' -> roundedRocks.add(new Point(x, y));
                    case '#' -> cubeShapedRocks.add(new Point(x, y));
                }
            }
        }
        var seen = new ArrayList<Set<Point>>();
        while (!seen.contains(roundedRocks)) {
            seen.add(Set.copyOf(roundedRocks));
            cycle(size, roundedRocks, cubeShapedRocks);
        }
        var cycleStart = seen.indexOf(roundedRocks);
        var finalRoundedRocks = seen.get(cycleStart + (1000000000 - cycleStart) % (seen.size() - cycleStart));
        return finalRoundedRocks.stream().mapToInt(p -> size - p.y).sum();
    }

    private static void tiltNorth(int size, Set<Point> roundedRocks, Set<Point> cubeShapedRocks) {
        for (var y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                var currentPoint = new Point(x, y);
                if (roundedRocks.contains(currentPoint)) {
                    while (true) {
                        var nextPoint = new Point(currentPoint.x, currentPoint.y - 1);
                        if (nextPoint.y < 0 || roundedRocks.contains(nextPoint) || cubeShapedRocks.contains(nextPoint)) {
                            break;
                        }
                        roundedRocks.remove(currentPoint);
                        currentPoint = nextPoint;
                        roundedRocks.add(currentPoint);
                    }
                }
            }
        }
    }

    private static void rotate (Set<Point> points, int height) {
        var newPoints = new HashSet<Point>();
        for (var point : points) {
            //noinspection SuspiciousNameCombination
            newPoints.add(new Point(height - point.y - 1, point.x));
        }
        points.clear();
        points.addAll(newPoints);
    }

    private static void cycle(int size, Set<Point> roundedRocks, Set<Point> cubeShapedRocks) {
        for (var i = 0; i < 4; i++) {
            tiltNorth(size, roundedRocks, cubeShapedRocks);
            rotate(roundedRocks, size);
            rotate(cubeShapedRocks, size);
        }
    }
}
