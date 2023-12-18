package org.anne.aoc2023;

import org.anne.common.Day;
import org.anne.common.Utils;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;

public class Day18 extends Day {
    public static void main(String[] args) {
        Day day = new Day18();
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }


    public static long part1(List<String> input) {
        var vertices = new ArrayList<Point>();
        var currentPoint = new Point(0, 0);
        vertices.add(currentPoint);
        for (var line : input) {
            var parts = line.split(" ");
            var nextPoint = getPoint(currentPoint, parts[0].charAt(0), Integer.parseInt(parts[1]));
            vertices.add(nextPoint);
            currentPoint = nextPoint;
        }
        return Utils.getArea(vertices);
    }

    public static long part2(List<String> input) {
        var vertices = new ArrayList<Point>();
        var currentPoint = new Point(0, 0);
        vertices.add(currentPoint);
        for (var line : input) {
            var pattern = Pattern.compile("\\(#([0-9a-f]+)\\)");
            var matcher = pattern.matcher(line);
            while (matcher.find()) {
                var color = matcher.group(1);
                var steps = Integer.parseInt(color.substring(0, color.length() - 1), 16);
                var nextPoint = getPoint(currentPoint, color.charAt(color.length() - 1), steps);
                vertices.add(nextPoint);
                currentPoint = nextPoint;
            }
        }
        return Utils.getArea(vertices);
    }

    private static Point getPoint(Point from, Character direction, int steps) {
        return switch (direction) {
            case 'R', '0' -> new Point(from.x + steps, from.y);
            case 'L', '2' -> new Point(from.x - steps, from.y);
            case 'U', '3' -> new Point(from.x, from.y - steps);
            case 'D', '1' -> new Point(from.x, from.y + steps);
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        };
    }
}