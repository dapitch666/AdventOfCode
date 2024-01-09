package org.anne.aoc2022;

import org.anne.common.Day;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Day12 extends Day {
    public static void main(String[] args) {
        Day day = new Day12();
        day.setName("Hill Climbing Algorithm");
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    public static int part1(List<String> input) {
        return findPath(getMap(input), false);
    }

    public static int part2(List<String> input) {
        return findPath(getMap(input), true);
    }

    private record HeightMap (Map<Point, Integer> map, Point start, Point end){}

    private static HeightMap getMap(List<String> input) {
        Map<Point, Integer> map = new HashMap<>();
        Point start = null;
        Point end = null;
        int i = 0;
        for (String line : input) {
            for (int j = 0; j < line.length(); j++) {
                switch (line.charAt(j)) {
                    case 'S' -> {
                        start = new Point(j, i);
                        map.put(new Point(j, i), (int) 'a');
                    }
                    case 'E' -> {
                        end = new Point(j, i);
                        map.put(new Point(j, i), (int) 'z');
                    }
                    default -> map.put(new Point(j, i), (int) line.charAt(j));
                }
            }
            i++;
        }
        return new HeightMap(map, start, end);
    }

    private static int findPath(HeightMap heightMap, boolean isPart2) {
        Map<Point, Integer> cost = new HashMap<>();
        Map<Point, Point> parent = new HashMap<>();
        Deque<Point> queue = new LinkedList<>();
        cost.put(heightMap.end, 0);
        queue.add(heightMap.end);
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            if (current.equals(heightMap.start) || (isPart2 && heightMap.map.get(current) == (int) 'a')) {
                int steps = 0;
                while (parent.containsKey(current)) {
                    steps++;
                    current = parent.get(current);
                }
                return steps;
            } else {
                for (Point neighbour : Arrays.asList(
                        new Point(current.x - 1, current.y),
                        new Point(current.x + 1, current.y),
                        new Point(current.x, current.y - 1),
                        new Point(current.x, current.y + 1)
                )) {
                    if (!heightMap.map.containsKey(neighbour) || heightMap.map.get(neighbour) < heightMap.map.get(current) - 1) {
                        continue;
                    }
                    int currentCost = cost.get(current) + 1;
                    if (currentCost < cost.getOrDefault(neighbour, Integer.MAX_VALUE)) {
                        cost.put(neighbour, currentCost);
                        parent.put(neighbour, current);
                        queue.add(neighbour);
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }
}
