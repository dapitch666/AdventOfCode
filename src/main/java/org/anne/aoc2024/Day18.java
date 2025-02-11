package org.anne.aoc2024;

import org.anne.common.Day;
import org.anne.common.GridHelper;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;

public class Day18 extends Day {
    public static void main(String[] args) {
        new Day18().run();
    }

    @Override
    public void execute() {
        setName("RAM Run");
        List<String> input = readFile();
        setPart1(part1(input, 71));
        setPart2(part2(input, 71));
    }

    public static int part1(List<String> input, int memorySize) {
        int bytes = memorySize == 71 ? 1024 : 12;
        char[][] memory = new char[memorySize][memorySize];
        Point start = new Point(0, 0);
        Point end = new Point(memorySize - 1, memorySize - 1);
        for (int i = 0; i < bytes; i++) {
            String[] parts = input.get(i).split(",");
            Point point = new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            memory[point.y][point.x] = '#';
        }
        return GridHelper.findShortestPath(memory, start, end, c -> c != '#').size() - 1;
    }

    public static String part2(List<String> input, int memorySize) {
        char[][] memory = new char[memorySize][memorySize];
        Point start = new Point(0, 0);
        Point end = new Point(memorySize - 1, memorySize - 1);
        Predicate<Character> isNotAWall = c -> c != '#';
        int bytes = memorySize == 71 ? 1024 : 12;
        List<Point> lastValidPath = Collections.emptyList();

        for (int i = 0; i < input.size(); i++) {
            String[] parts = input.get(i).split(",");
            Point point = new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            memory[point.y][point.x] = '#';
            if (i == bytes) {
                lastValidPath = GridHelper.findShortestPath(memory, start, end, isNotAWall);
            }
            if (i > bytes && lastValidPath.contains(point)) { // If the new obstacle is in the last valid path
                lastValidPath = GridHelper.findShortestPath(memory, start, end, isNotAWall);
                if (lastValidPath.isEmpty()) {
                    return input.get(i);
                }
            }
        }
        return "";
    }
}
