package org.anne.aoc2016;

import org.anne.common.Day;
import org.anne.common.Direction;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Day1 extends Day {
    public static void main(String[] args) {
        new Day1().run();
    }

    @Override
    public void execute() {
        setName("No Time for a Taxicab");
        String input = readFileOneLine();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(String input) {
        Point point = new Point(0, 0);
        Direction direction = Direction.NORTH;
        for (String s : input.split(", ")) {
            direction = direction.rotate90(s.startsWith("R"));
            int distance = Integer.parseInt(s.substring(1));
            point.translate(distance * direction.dx, distance * direction.dy);
        }
        return Math.abs(point.x) + Math.abs(point.y);
    }

    public static int part2(String input) {
        Point point = new Point(0, 0);
        Direction direction = Direction.NORTH;
        Set<Point> visitedPoints = new HashSet<>();
        visitedPoints.add(point);
        for (String s : input.split(", ")) {
            direction = direction.rotate90(s.startsWith("R"));
            int distance = Integer.parseInt(s.substring(1));
            for (int i = 0; i < distance; i++) {
                point.translate(direction.dx, direction.dy);
                if (!visitedPoints.add(new Point(point))) {
                    return Math.abs(point.x) + Math.abs(point.y);
                }
            }
        }
        return 0;
    }
}