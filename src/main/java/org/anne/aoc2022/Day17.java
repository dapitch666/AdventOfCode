package org.anne.aoc2022;

import org.anne.common.Day;
import org.anne.common.Utils;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day17 extends Day {
    public static void main(String[] args) {
        Day day = new Day17();
        String input = day.readFileOneLine();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    public static long part1(String input) {
        return tetris(input, 2022);
    }

    public static long part2(String input) {
        return tetris(input, 1000000000000L);
    }

    private static long tetris(String input, long maxRocks) {
        Rock cave = new Rock(IntStream.range(0, 7).mapToObj(x -> new Coordinates(x, 0)).collect(Collectors.toList()));
        if (input.length() < 200) input = input.repeat(200 / input.length());

        char[] chars = input.toCharArray();
        long height = 0, rocks = 0;
        long firstCycleHeight = 0, firstCycleRocks = 0;
        long nextCycleHeight = 0, nextCycleRocks = 0;

        Rock s = new Rock(shapes.get(0), new Coordinates(2, 4));
        for(int i = 0, shapeIndex = 0; rocks < maxRocks; i++) {
            if(i>=chars.length) i = 0;

            if (firstCycleRocks == 0 && i == 0 && rocks > 0) {
                firstCycleRocks = rocks;
                firstCycleHeight = height;
            } else if (firstCycleRocks > 0 && nextCycleRocks == 0 && i == 0 && rocks > firstCycleRocks) {
                nextCycleRocks = rocks;
                nextCycleHeight = height;
                long rocksPerCycle = nextCycleRocks - firstCycleRocks;
                long numberOfCycles = (maxRocks - firstCycleRocks) / rocksPerCycle - 1;
                long addHeight = (nextCycleHeight - firstCycleHeight) * numberOfCycles;

                rocks += rocksPerCycle * numberOfCycles;
                height += addHeight;

                cave = cave.translate(0, addHeight);
                s = s.translate(0, addHeight);
            }

            char c = chars[i];
            Rock moved = s.translate((c == '>' ? 1 : -1), 0);
            if(moved.isBetweenWalls() && moved.points.stream().noneMatch(cave::contains)) {
                s = moved;
            }
            moved = s.translate(0, -1);
            if(moved.points.stream().noneMatch(cave::contains)) {
                s = moved;
            } else {
                cave.addAll(s.points);
                shapeIndex = (shapeIndex + 1) % shapes.size();
                height = Math.max(s.topY(), height);
                s = new Rock(shapes.get(shapeIndex), new Coordinates(2, 4 + height));
                rocks++;
            }
        }
        return height;
    }

    static class Coordinates {
        int x;
        long y;

        public Coordinates(int x, long y) {
            this.x = x;
            this.y = y;
        }

        public Coordinates(Coordinates source, Coordinates origin) {
            this.x = source.x + origin.x;
            this.y = source.y + origin.y;
        }

        public void shiftUp(long y) {
            this.y += y;
        }

        public int getX() {
            return x;
        }

        public long getY() {
            return y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinates that = (Coordinates) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public record Rock(List<Coordinates> points) {
        public Rock(Rock shape, Coordinates initialPosition) {
            this(new ArrayList<>());
            for (Coordinates p : shape.points) {
                this.points.add(new Coordinates(p, initialPosition));
            }
        }

        public Rock translate(int x, long y) {
            List<Coordinates> points = new ArrayList<>();
            for (Coordinates p : this.points) {
                points.add(new Coordinates(p, new Coordinates(x, y)));
            }
            return new Rock(points);
        }

        boolean isBetweenWalls() {
            return points.stream().mapToInt(Coordinates::getX).max().orElseThrow() <= 6
                    && points.stream().mapToLong(Coordinates::getX).min().orElseThrow() >= 0;
        }

        long topY() {
            return points.stream().mapToLong(p -> p.y).max().orElse(0);
        }

        public boolean contains(Coordinates coordinates) {
            return this.points.contains(coordinates);
        }

        public void addAll(List<Coordinates> points) {
            this.points.addAll(points);
        }
    }
    
    static List<Rock> shapes = Arrays.asList(
            new Rock(Arrays.asList(
                    new Coordinates(0, 0),
                    new Coordinates(1, 0),
                    new Coordinates(2, 0),
                    new Coordinates(3, 0))),
            new Rock(Arrays.asList(
                    new Coordinates(1, 0),
                    new Coordinates(0, 1),
                    new Coordinates(1, 1),
                    new Coordinates(2, 1),
                    new Coordinates(1, 2))),
            new Rock(Arrays.asList(
                    new Coordinates(0, 0),
                    new Coordinates(1, 0),
                    new Coordinates(2, 0),
                    new Coordinates(2, 1),
                    new Coordinates(2, 2))),
            new Rock(Arrays.asList(
                    new Coordinates(0, 0),
                    new Coordinates(0, 1),
                    new Coordinates(0, 2),
                    new Coordinates(0, 3))),
            new Rock(Arrays.asList(
                    new Coordinates(0, 0),
                    new Coordinates(0, 1),
                    new Coordinates(1, 1),
                    new Coordinates(1, 0)))
    );
}
