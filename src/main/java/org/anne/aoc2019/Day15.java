package org.anne.aoc2019;

import org.anne.common.Day;

import java.awt.*;
import java.util.*;
import java.util.List;

import static org.anne.common.Utils.manhattanDistance;

public class Day15 extends Day {

    public static void main(String[] args) {
        Day day = new Day15();
        String input = day.readFileOneLine();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    static int part1(String input) {
        Map<Point, Character> map = getMapOf(input);
        Point oxygen = findOxygen(map);
        return path(map, new Point(0, 0), oxygen).size();
    }

    static int part2(String input) {
        Map<Point, Character> map = getMapOf(input);
        return minutesToFill(map);
    }

    static int minutesToFill(Map<Point, Character> map) {
        int minutes = 0;
        List<Point> oxygen = new ArrayList<>();
        while (map.containsValue('.')) {
            if (oxygen.isEmpty()) {
                oxygen.add(findOxygen(map));
            }
            List<Point> newOxygen = new ArrayList<>();
            for (var point : oxygen) {
                for (var neighbor : getNeighbors(point)) {
                    if (map.get(neighbor) == '.') {
                        map.put(neighbor, 'O');
                        newOxygen.add(neighbor);
                    }
                }
            }
            oxygen = newOxygen;
            minutes++;
        }
        return minutes;
    }

    private static Map<Point, Character> getMapOf(String input) {
        Map<Point, Character> map = new HashMap<>();
        Point current = new Point(0, 0);
        Computer computer = new Computer(input);
        map.put(current, '.');
        while (true) {
            var next = getClosestUncharted(map, current);
            if (next == null) {
                break;
            }
            if (!areNeighbors(current, next)) {
                var adjacent = findNeighbor(map, next);
                var path = path(map, current, adjacent);
                for (var point : path) {
                    computer.computeAndGetOutput(Direction.getDirectionBetween(current, point));
                    current = point;
                }
            }
            var status = (int) computer.computeAndGetOutput(Direction.getDirectionBetween(current, next));
            switch (status) {
                case 0 -> map.put(next, '#');
                case 1 -> {
                    map.put(next, '.');
                    current = next;
                }
                case 2 -> {
                    map.put(next, 'O');
                    current = next;
                }
            }
        }
        return map;
    }

    private static Point findOxygen(Map<Point, Character> map) {
        return map.entrySet().stream()
                .filter(entry -> entry.getValue().equals('O'))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow();
    }

    static Point findNeighbor(Map<Point, Character> map, Point next) {
        return map.keySet().stream()
                .filter(a -> map.get(a) == '.')
                .filter(a -> areNeighbors(a, next))
                .findFirst()
                .orElseThrow();
    }

    private static boolean areNeighbors(Point a, Point b) {
        return manhattanDistance(a, b) == 1;
    }

    static Point getClosestUncharted(Map<Point, Character> map, Point point) {
        int minDistance = Integer.MAX_VALUE;
        Point closest = null;
        for (Point p : map.keySet().stream().filter(e -> map.get(e) == '.').toList()) {
            for (Point neighbor : getNeighbors(p)) {
                if (!map.containsKey(neighbor) && manhattanDistance(neighbor, point) < minDistance) {
                    minDistance = (int) manhattanDistance(neighbor, point);
                    closest = neighbor;
                }
            }
        }
        return closest;
    }

    private static List<Point> getNeighbors(Point point) {
        List<Point> neighbors = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            switch (direction) {
                case NORTH -> neighbors.add(new Point(point.x, point.y - 1));
                case SOUTH -> neighbors.add(new Point(point.x, point.y + 1));
                case WEST -> neighbors.add(new Point(point.x - 1, point.y));
                case EAST -> neighbors.add(new Point(point.x + 1, point.y));
            }
        }
        return neighbors;
    }

    private static List<Point> path(Map<Point, Character> map, Point from, Point to) {
        HashMap<Point, Integer> distances = new HashMap<>();
        List<Point> path = new ArrayList<>();
        distances.put(from, 0);
        HashMap<Point, Point> previous = new HashMap<>();
        PriorityQueue<Point> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        queue.add(from);
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            if (current.equals(to)) {
                while (previous.containsKey(current)) {
                    path.add(current);
                    current = previous.get(current);
                }
                break;
            }
            for (Point neighbor : getNeighbors(current)) {
                if (map.containsKey(neighbor) && map.get(neighbor) != '#') {
                    int newDistance = distances.get(current) + 1;
                    if (!distances.containsKey(neighbor) || newDistance < distances.get(neighbor)) {
                        distances.put(neighbor, newDistance);
                        previous.put(neighbor, current);
                        queue.add(neighbor);
                    }
                }
            }
        }
        Collections.reverse(path);
        return path;
    }

    enum Direction {
        NORTH(1), SOUTH(2), WEST(3), EAST(4);
        final int code;

        Direction(int code) {
            this.code = code;
        }

        static int getDirectionBetween(Point from, Point to) {
            if (from.x == to.x) {
                if (from.y > to.y) {
                    return NORTH.code;
                } else {
                    return SOUTH.code;
                }
            } else {
                if (from.x > to.x) {
                    return WEST.code;
                } else {
                    return EAST.code;
                }
            }
        }
    }
}
