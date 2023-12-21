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
        var maxX = input.get(0).length();
        var maxY = input.size();
        var rocks = new ArrayList<Point>();
        var start = new Point();
        var queue = new HashSet<Point>();
        for (var y = 0; y < maxY; y++) {
            for (var x = 0; x < maxX; x++) {
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
                    if (rocks.contains(nextPoint) || nextPoint.y < 0 || nextPoint.y >= maxY || nextPoint.x < 0 || nextPoint.x >= maxX) {
                        continue;
                    }
                    newQueue.add(nextPoint);
                }
            }
            queue = newQueue;
        }
        return queue.size();
    }

    public static int part2(List<String> input, int steps) {
        return 0;
    }
}
