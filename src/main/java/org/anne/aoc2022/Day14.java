package org.anne.aoc2022;

import org.anne.common.Day;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class Day14 extends Day {
    public static void main(String[] args) {
        Day day = new Day14();
        day.setName("Regolith Reservoir");
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    public static int part1(List<String> input) {
        return getUnitsOfSand(input, true);
    }

    public static int part2(List<String> input) {
        return getUnitsOfSand(input, false);
    }

    private static int getUnitsOfSand (List<String> input, boolean isPart1) {
        int result = 0;
        Set<Point> occupied = drawCave(input);
        int floor = occupied.stream().mapToInt(p -> p.y).max().orElseThrow() + (isPart1 ? 0 : 2);
        if (!isPart1) {
            IntStream.range(500 - (floor + 1), 500 + (floor + 1)).forEach(i -> occupied.add(new Point(i, floor)));
        }
        Point sand = new Point(500, 0);
        do {
            if (!occupied.contains(new Point(sand.x, sand.y + 1))) {
                sand.translate(0, 1);
            } else if (!occupied.contains(new Point(sand.x - 1, sand.y + 1))) {
                sand.translate(-1, 1);
            } else if (!occupied.contains(new Point(sand.x + 1, sand.y + 1))) {
                sand.translate(1, 1);
            } else {
                occupied.add(sand);
                result++;
                sand = new Point(500, 0);
            }
        } while (isPart1 ? sand.y < floor : !occupied.contains(new Point(500, 0)));
        return result;
    }

    static Set<Point> drawCave(List<String> input) {
        Set<Point> cave = new HashSet<>();
        for (String line : input) {
            String[] splitLine = line.split(" -> ");
            for (int i = 1; i < splitLine.length; i++) {
                Point start = getPoint(splitLine[i-1]);
                Point end = getPoint(splitLine[i]);
                if (start.x == end.x) {
                    int yMin = Math.min(start.y, end.y);
                    int yMax = Math.max(start.y, end.y);
                    for (int y = yMin; y <= yMax; y++) {
                        cave.add(new Point(start.x, y));
                    }
                } else {
                    int xMin = Math.min(start.x, end.x);
                    int xMax = Math.max(start.x, end.x);
                    for (int x = xMin; x <= xMax; x++) {
                        cave.add(new Point(x, start.y));
                    }
                }
            }
        }
        return cave;
    }

    private static Point getPoint(String coordinates) {
        String[] splitCoordinates = coordinates.split(",");
        return new Point(Integer.parseInt(splitCoordinates[0]), Integer.parseInt(splitCoordinates[1]));
    }
}
