package org.anne.aoc2022;

import org.anne.common.Day;
import org.anne.common.Point3d;

import java.util.*;

public class Day18 extends Day {
    public static void main(String[] args) {
        Day day = new Day18();
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    public static long part1(List<String> input) {
        List<Point3d> cubes = getCubes(input);
        return (cubes.size() * 6L) - cubes.stream()
                                        .flatMap(c -> getNeighbors(c).stream())
                                        .filter(cubes::contains)
                                        .count();
    }

    public static long part2(List<String> input) {
        List<Point3d> cubes = getCubes(input);
        Deque<Point3d> queue = new LinkedList<>();

        Point3d start = getNeighbors(cubes.stream()
                    .min(Comparator.comparingInt(o -> o.x() + o.y() + o.z()))
                    .orElseThrow())
                        .stream()
                        .filter(c -> !cubes.contains(c))
                        .findFirst()
                        .orElseThrow();

        queue.add(start);
        HashSet<Point3d> air = new HashSet<>();
        while(queue.size() > 0) {
            Point3d current = queue.poll();
            air.add(current);

            for(Point3d neighbor : getNeighbors(current)) {
                if(air.contains(neighbor) || cubes.contains(neighbor) || queue.contains(neighbor)
                    || cubes.stream().mapToInt(c -> c.manhattanDistance(neighbor)).min().orElseThrow() > 2) {
                    continue;
                }
                queue.add(neighbor);
            }
        }
        return air.stream()
                .flatMap(c -> getNeighbors(c).stream())
                .filter(cubes::contains)
                .count();
    }

    private static List<Point3d> getCubes(List<String> input) {
        List<Point3d> cubes = new ArrayList<>();
        for (String line : input) {
            String[] params = line.split(",");
            cubes.add(new Point3d(Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[2])));
        }
        return cubes;
    }

    private static List<Point3d> getNeighbors(Point3d point) {
        return List.of(
                new Point3d(point.x() - 1, point.y(), point.z()),
                new Point3d(point.x() + 1, point.y(), point.z()),
                new Point3d(point.x(), point.y() - 1, point.z()),
                new Point3d(point.x(), point.y() + 1, point.z()),
                new Point3d(point.x(), point.y(), point.z() - 1),
                new Point3d(point.x(), point.y(), point.z() + 1)
        );
    }
}
