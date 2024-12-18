package org.anne.aoc2024;

import org.anne.common.Day;
import org.anne.common.Direction;
import org.anne.common.GridHelper;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Day18 extends Day {
    public static void main(String[] args) {
        Day day = new Day18();
        day.setName("RAM Run");
        List<String> input = day.readFile();
        day.setPart1(part1(input, 71));
        day.setPart2(part2(input, 71));
        day.printParts();
    }

    public static int part1(List<String> input, int memorySize) {
        int bytes = memorySize == 71 ? 1024 : 12;
        char[][] memory = new char[memorySize][memorySize];
        for (int i = 0; i < bytes; i++) {
            String[] parts = input.get(i).split(",");
            Point point = new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            memory[point.y][point.x] = '#';
        }
        return findShortestPath(memorySize, memory).size() - 1;
    }

    private static List<Point> findShortestPath(int memorySize, char[][] memory) {
        Point start = new Point(0, 0);
        Point end = new Point(memorySize - 1, memorySize - 1);
        boolean[][] visited = new boolean[memorySize][memorySize];
        Map<Point, Point> parentMap = new HashMap<>();
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        visited[start.y][start.x] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            if (current.equals(end)) {
                List<Point> path = new LinkedList<>();
                for (Point at = end; at != null; at = parentMap.get(at)) {
                    path.add(at);
                }
                return path;
            }
            for (Direction direction : Direction.values()) {
                Point newPoint = new Point(Direction.getPoint(direction, current));
                if (GridHelper.isValidPoint(newPoint, memorySize) && !visited[newPoint.y][newPoint.x] && memory[newPoint.y][newPoint.x] != '#') {
                    queue.add(newPoint);
                    visited[newPoint.y][newPoint.x] = true;
                    parentMap.put(newPoint, current);
                }
            }
        }

        return Collections.emptyList(); // Return an empty list if there is no path
    }

    public static String part2(List<String> input, int memorySize) {
        char[][] memory = new char[memorySize][memorySize];
        int bytes = memorySize == 71 ? 1024 : 12;
        List<Point> lastValidPath = Collections.emptyList();

        for (int i = 0; i < input.size(); i++) {
            String[] parts = input.get(i).split(",");
            Point point = new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            memory[point.y][point.x] = '#';
            if (i == bytes) {
                lastValidPath = findShortestPath(memorySize, memory);
            }
            if (i > bytes && lastValidPath.contains(point)) { // If the new obstacle is in the last valid path
                lastValidPath = findShortestPath(memorySize, memory);
                if (lastValidPath.isEmpty()) {
                    return input.get(i);
                }
            }
        }
        return "";
    }
}
