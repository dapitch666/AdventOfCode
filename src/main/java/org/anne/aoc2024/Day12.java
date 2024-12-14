package org.anne.aoc2024;

import org.anne.common.Day;
import org.anne.common.Direction;
import org.anne.common.GridHelper;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Day12 extends Day {
    public static void main(String[] args) {
        Day day = new Day12();
        day.setName("Garden Groups");
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    public static int part1(List<String> input) {
        Map<Character, List<Region>> regions = getRegions(input);
        return regions.values().stream()
                .flatMap(Collection::stream)
                .mapToInt(region -> region.plots.size() * region.perimeter())
                .sum();
    }

    public static int part2(List<String> input) {
        Map<Character, List<Region>> regions = getRegions(input);
        return regions.values().stream()
                .flatMap(Collection::stream)
                .mapToInt(region -> region.plots.size() * region.corners())
                .sum();
    }

    private static Map<Character, List<Region>> getRegions(List<String> input) {
        var grid = GridHelper.getCharGrid(input);
        int gridSize = grid.length;
        Map<Character, List<Region>> regions = new HashMap<>();

        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                var type = grid[y][x];
                var point = new Point(x, y);
                regions.computeIfAbsent(type, k -> new ArrayList<>());
                var regionsToMerge = new ArrayList<Region>();

                for (Region region : regions.get(type)) {
                    if (region.isAdjacent(point)) {
                        regionsToMerge.add(region);
                    }
                }

                if (regionsToMerge.isEmpty()) {
                    regions.get(type).add(new Region(new HashSet<>(List.of(point))));
                } else {
                    Region mainRegion = regionsToMerge.get(0);
                    mainRegion.plots.add(point);
                    for (int i = 1; i < regionsToMerge.size(); i++) {
                        mainRegion.merge(regionsToMerge.get(i));
                        regions.get(type).remove(regionsToMerge.get(i));
                    }
                }
            }
        }
        return regions;
    }

    static boolean containsNone(Set<Point> set, List<Point> points) {
        return points.stream().noneMatch(set::contains);
    }

    record Region(Set<Point> plots) {
        boolean isAdjacent(Point point) {
            return Arrays.stream(Direction.values())
                    .map(direction -> Direction.getPoint(direction, point))
                    .anyMatch(plots::contains);
        }

        void merge(Region other) {
            plots.addAll(other.plots);
        }

        int perimeter() {
            int perimeter = 0;
            for (Point plot : plots) {
                for (Direction direction : Direction.values()) {
                    Point neighbor = Direction.getPoint(direction, plot);
                    if (!plots.contains(neighbor)) {
                        perimeter++;
                    }
                }
            }
            return perimeter;
        }

        int corners() {
            int total = 0;

            for (final Point point : plots) {
                int corners = 0;
                Point north = Direction.getPoint(Direction.NORTH, point);
                Point south = Direction.getPoint(Direction.SOUTH, point);
                Point east = Direction.getPoint(Direction.EAST, point);
                Point west = Direction.getPoint(Direction.WEST, point);
                Point northEast = Direction.getPoint(Direction.EAST, north);
                Point northWest = Direction.getPoint(Direction.WEST, north);
                Point southEast = Direction.getPoint(Direction.EAST, south);
                Point southWest = Direction.getPoint(Direction.WEST, south);

                if (containsNone(plots, List.of(west, north))
                        || !plots.contains(northWest) && plots.containsAll(List.of(west, north))) {
                    corners++;
                }
                if (containsNone(plots, List.of(east, north))
                        || !plots.contains(northEast) && plots.containsAll(List.of(east, north))) {
                    corners++;
                }
                if (containsNone(plots, List.of(east,south))
                        || !plots.contains(southEast) && plots.containsAll(List.of(east, south))) {
                    corners++;
                }
                if (containsNone(plots, List.of(west, south))
                        || !plots.contains(southWest) && plots.containsAll(List.of(west, south))) {
                    corners++;
                }
                total += corners;
            }

            return total;
        }
    }
}