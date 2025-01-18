package org.anne.aoc2018;

import org.anne.common.Day;
import org.anne.common.Point3d;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day23 extends Day {
    public static void main(String[] args) {
        new Day23().run();
    }

    @Override
    public void execute() {
        setName("Experimental Emergency Teleportation");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static long part1(List<String> input) {
        List<Nanobot> nanobots = getNanobots(input);
        Nanobot strongest = nanobots.stream().max(Comparator.comparingInt(n -> n.range)).orElseThrow();
        return nanobots.stream().filter(strongest::inRange).count();
    }

    public static int part2(List<String> input) {
        List<Nanobot> bots = getNanobots(input);
        Point3d origin = new Point3d(0, 0, 0);

        int max = Stream.iterate(1, size -> size * 2)
                .filter(size -> size > bots.stream().mapToInt(Nanobot::maxAbsReach).max().orElseThrow())
                .findFirst().orElseThrow();

        PriorityQueue<Box> queue = new PriorityQueue<>();
        queue.add(new Box(max, bots.size()));

        while (!queue.isEmpty()) {
            Box box = queue.poll();
            if (box.size == 1) {
                return box.point.manhattanDistance(origin);
            }
            int size = box.size / 2;
            box.next(size).forEach(next -> queue.add(new Box(next, size, reach(next, size, bots))));
        }
        return 0;
    }

    private static List<Nanobot> getNanobots(List<String> input) {
        return input.stream().map(line -> {
            Matcher matcher = Pattern.compile("-?\\d+").matcher(line);
            int[] ints = matcher.results().mapToInt(m -> Integer.parseInt(m.group())).toArray();
            return new Nanobot(new Point3d(ints[0], ints[1], ints[2]), ints[3]);
        }).toList();
    }

    static int reach(Point3d point, int size, List<Nanobot> bots) {
        return (int) bots.stream().filter(n -> n.inRange(point, size)).count();
    }

    record Nanobot(Point3d position, int range) {
        public boolean inRange(Nanobot other) {
            return position.manhattanDistance(other.position) <= range;
        }

        public int maxAbsReach() {
            return Math.max(Math.max(Math.abs(position.x()), Math.abs(position.y())), Math.abs(position.z())) + range;
        }

        public boolean inRange(Point3d point, int size) {
            int d = (position.manhattanDistance(point) + position.manhattanDistance(point.add(size)) - (size + 1) * 3) / 2;
            return d <= range;
        }
    }

    record Box(Point3d point, int size, int reach) implements Comparable<Box> {
        Box(int i, int reach) {
            this(new Point3d(-i, -i, -i), i * 2, reach);
        }

        List<Point3d> next(int size) {
            List<Point3d> next = new ArrayList<>();
            for (int z = 0; z < 2; z++) {
                for (int y = 0; y < 2; y++) {
                    for (int x = 0; x < 2; x++) {
                        next.add(point.add(new Point3d(x, y, z).multiply(size)));
                    }
                }
            }
            return next;
        }

        @Override
        public int compareTo(@NotNull Box other) {
            return Integer.compare(other.reach, this.reach);
        }
    }
}