package org.anne.aoc2018;

import org.anne.common.Day;
import org.anne.common.Utils;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


public class Day6 extends Day {
    public static void main(String[] args) {
        new Day6().run();
    }

    @Override
    public void execute() {
        setName("Chronal Coordinates");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input, 10000));
        printParts();
    }

    public static int part1(List<String> input) {
        List<Point> coordinates = getPoints(input);
        int maxX = coordinates.stream().mapToInt(p -> p.x).max().orElse(0) + 1;
        int maxY = coordinates.stream().mapToInt(p -> p.y).max().orElse(0) + 1;

        Map<Point, Integer> areas = new HashMap<>();
        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                Point currentPoint = new Point(x, y);
                int minDistance = Integer.MAX_VALUE;
                int minIndex = -1;
                boolean isTie = false;
                for (int n = 0; n < coordinates.size(); n++) {
                    int distance = Utils.manhattanDistance(coordinates.get(n), currentPoint);
                    if (distance < minDistance) {
                        minDistance = distance;
                        minIndex = n;
                        isTie = false;
                    } else if (distance == minDistance) {
                        isTie = true;
                    }
                }
                areas.put(currentPoint, isTie ? -1 : minIndex);
            }
        }

        Set<Integer> infiniteAreas = new HashSet<>(List.of(-1));
        for (int x = 0; x < maxX; x++) {
            infiniteAreas.add(areas.getOrDefault(new Point(x, maxY - 1), -1));
            infiniteAreas.add(areas.getOrDefault(new Point(x, 0), -1));
        }
        for (int y = 0; y < maxY; y++) {
            infiniteAreas.add(areas.getOrDefault(new Point(maxX - 1, y), -1));
            infiniteAreas.add(areas.getOrDefault(new Point(0, y), -1));
        }

        return areas.values().stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> !infiniteAreas.contains(entry.getKey()))
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getValue)
                .map(Math::toIntExact)
                .orElse(0);
    }

    public static long part2(List<String> input, int maxDistance) {
        List<Point> coordinates = getPoints(input);
        int maxX = coordinates.stream().mapToInt(p -> p.x).max().orElse(0) + 1;
        int maxY = coordinates.stream().mapToInt(p -> p.y).max().orElse(0) + 1;
        long regionSize = 0;
        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                Point point = new Point(x, y);
                int totalDistance = coordinates.stream()
                        .mapToInt(p -> Utils.manhattanDistance(p, point))
                        .sum();
                if (totalDistance < maxDistance) {
                    regionSize++;
                }
            }
        }
        return regionSize;
    }

    private static List<Point> getPoints(List<String> input) {
        return input.stream()
                .map(line -> line.split(", "))
                .map(parts -> new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])))
                .collect(Collectors.toList());
    }
}
