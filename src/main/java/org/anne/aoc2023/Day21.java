package org.anne.aoc2023;

import org.anne.common.Day;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Day21 extends Day {
    public static void main(String[] args) {
        Day day = new Day21();
        List<String> input = day.readFile();
        day.setPart1(part1(input, 64));
        day.setPart2(part2(input, 26501365));
        day.printParts();
    }


    public static int part1(List<String> input, int steps) {
        var size = input.size();
        var rocks = new ArrayList<Point>();
        var start = new Point();
        var queue = new HashSet<Point>();
        for (var y = 0; y < size; y++) {
            for (var x = 0; x < size; x++) {
                switch (input.get(y).charAt(x)) {
                    case 'S' -> start = new Point(x, y);
                    case '#' -> rocks.add(new Point(x, y));
                }
            }
        }
        queue.add(start);
        for (var i = 0; i < steps; i++) {
            var newQueue = new HashSet<Point>();
            for (var currentPoint : queue) {
                for (var nextPoint : Arrays.asList(
                        new Point(currentPoint.x + 1, currentPoint.y),
                        new Point(currentPoint.x - 1, currentPoint.y),
                        new Point(currentPoint.x, currentPoint.y + 1),
                        new Point(currentPoint.x, currentPoint.y - 1)
                )) {
                    if (!rocks.contains(nextPoint) && nextPoint.y >= 0 && nextPoint.y < size && nextPoint.x >= 0 && nextPoint.x < size) {
                        newQueue.add(nextPoint);
                    }
                }
            }
            queue = newQueue;
        }
        return queue.size();
    }

    public static long part2(List<String> input, int steps) {
        var size = input.size();
        var rocks = new ArrayList<Point>();
        var start = new Point();
        var queue = new HashSet<Point>();
        var visited = new HashMap<Point, Integer>();

        for (var y = 0; y < size; y++) {
            for (var x = 0; x < size; x++) {
                switch (input.get(y).charAt(x)) {
                    case 'S' -> {
                        start = new Point(x, y);
                        visited.put(start, 0);
                    }
                    case '#' -> rocks.add(new Point(x, y));
                }
            }
        }
        queue.add(start);
        for (var i = 1; i < size + 1; i++) {
            var newQueue = new HashSet<Point>();
            for (var currentPoint : queue) {
                for (var nextPoint : Arrays.asList(
                        new Point(currentPoint.x + 1, currentPoint.y),
                        new Point(currentPoint.x - 1, currentPoint.y),
                        new Point(currentPoint.x, currentPoint.y + 1),
                        new Point(currentPoint.x, currentPoint.y - 1)
                )) {
                    if (!rocks.contains(nextPoint) && !visited.containsKey(nextPoint) &&
                            nextPoint.y >= 0 && nextPoint.y < size && nextPoint.x >= 0 && nextPoint.x < size) {
                        newQueue.add(nextPoint);
                        visited.put(nextPoint, i);
                    }
                }
            }
            queue = newQueue;
        }

        var odd = visited.values().stream().filter(v -> v % 2 == 1).count();
        var even = visited.values().stream().filter(v -> v % 2 == 0).count();
        var oddCorner = visited.values().stream().filter(v -> v % 2 == 1 && v > size / 2).count();
        var evenCorner = visited.values().stream().filter(v -> v % 2 == 0 && v > size / 2).count();

        long n = steps / size;
        return ((n + 1) * (n + 1) * odd) + (n * n * even) - ((n + 1) * oddCorner) + (n * evenCorner);
    }
}