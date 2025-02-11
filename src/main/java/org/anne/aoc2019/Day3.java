package org.anne.aoc2019;

import org.anne.common.Day;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day3 extends Day {
    public static void main(String[] args) {
        new Day3().run();
    }

    @Override
    public void execute() {
        setName("Crossed Wires");
        List<String> input = readFile();
        Map<Point, Integer> wire1 = mapWire(input.get(0));
        Map<Point, Integer> wire2 = mapWire(input.get(1));
        setPart1(part1(wire1, wire2));
        setPart2(part2(wire1, wire2));
    }

    static long part1(Map<Point, Integer> wire1, Map<Point, Integer> wire2) {
        int minDistance = Integer.MAX_VALUE;
        wire2.keySet().retainAll(wire1.keySet());
        for (Point point : wire2.keySet()) {
            minDistance = Math.min(minDistance, Math.abs(point.x) + Math.abs(point.y));
        }
        return minDistance;
    }

    static int part2(Map<Point, Integer> wire1, Map<Point, Integer> wire2) {
        int minStep = Integer.MAX_VALUE;
        wire2.keySet().retainAll(wire1.keySet());
        for (Point point : wire2.keySet()) {
            minStep = Math.min(minStep, wire1.get(point) + wire2.get(point));
        }
        return minStep;
    }

    static Map<Point, Integer> mapWire(String input) {
        Map<Point, Integer> wire = new HashMap<>();
        String[] path = input.split(",");
        Point current = new Point(0, 0);
        int d = 0;
        for (String s : path) {
            Point direction = getDir(s.charAt(0));
            int distance = Integer.parseInt(s.substring(1));
            for (int i = 0; i < distance; i++) {
                Point newPoint = new Point(current.x + direction.x, current.y + direction.y);
                wire.put(newPoint, ++d);
                current = new Point(newPoint);
            }
        }
        return wire;
    }

    private static Point getDir (char c) {
        return switch (c) {
            case 'U' -> new Point(0, 1);
            case 'D' -> new Point(0, -1);
            case 'L' -> new Point(-1, 0);
            case 'R' -> new Point(1, 0);
            default -> null;
        };
    }
}