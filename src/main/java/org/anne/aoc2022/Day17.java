package org.anne.aoc2022;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day17 extends Day {
    public static void main(String[] args) {
        new Day17().run();
    }

    @Override
    public void execute() {
        setName("Pyroclastic Flow");
        String input = readFileOneLine();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static long part1(String input) {
        return tetris(input, 2022);
    }

    public static long part2(String input) {
        return tetris(input, 1000000000000L);
    }

    private static long tetris(String input, long maxRocks) {
        Rock cave = new Rock(IntStream.range(0, 7).mapToObj(x -> new PointLongY(x, 0)).collect(Collectors.toList()));
        if (input.length() < 200) input = input.repeat(200 / input.length());

        char[] chars = input.toCharArray();
        long height = 0, rocks = 0;
        long firstCycleHeight = 0, firstCycleRocks = 0;
        long nextCycleHeight, nextCycleRocks = 0;

        Rock s = new Rock(shapes.get(0), new PointLongY(2, 4));
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
                s = new Rock(shapes.get(shapeIndex), new PointLongY(2, 4 + height));
                rocks++;
            }
        }
        return height;
    }

    public record PointLongY (int x, long y) {
        public PointLongY(PointLongY source, PointLongY origin) {
            this(source.x + origin.x, source.y + origin.y);
        }
    }

    public record Rock(List<PointLongY> points) {
        public Rock(Rock shape, PointLongY initialPosition) {
            this(new ArrayList<>());
            for (PointLongY p : shape.points) {
                this.points.add(new PointLongY(p, initialPosition));
            }
        }

        public Rock translate(int x, long y) {
            List<PointLongY> points = new ArrayList<>();
            for (PointLongY p : this.points) {
                points.add(new PointLongY(p, new PointLongY(x, y)));
            }
            return new Rock(points);
        }

        boolean isBetweenWalls() {
            return points.stream().mapToInt(p -> p.x).max().orElseThrow() <= 6
                    && points.stream().mapToInt(p -> p.x).min().orElseThrow() >= 0;
        }

        long topY() {
            return points.stream().mapToLong(p -> p.y).max().orElse(0);
        }

        public boolean contains(PointLongY point) {
            return this.points.contains(point);
        }

        public void addAll(List<PointLongY> points) {
            this.points.addAll(points);
        }
    }
    
    static final List<Rock> shapes = Arrays.asList(
            new Rock(Arrays.asList(
                    new PointLongY(0, 0),
                    new PointLongY(1, 0),
                    new PointLongY(2, 0),
                    new PointLongY(3, 0))),
            new Rock(Arrays.asList(
                    new PointLongY(1, 0),
                    new PointLongY(0, 1),
                    new PointLongY(1, 1),
                    new PointLongY(2, 1),
                    new PointLongY(1, 2))),
            new Rock(Arrays.asList(
                    new PointLongY(0, 0),
                    new PointLongY(1, 0),
                    new PointLongY(2, 0),
                    new PointLongY(2, 1),
                    new PointLongY(2, 2))),
            new Rock(Arrays.asList(
                    new PointLongY(0, 0),
                    new PointLongY(0, 1),
                    new PointLongY(0, 2),
                    new PointLongY(0, 3))),
            new Rock(Arrays.asList(
                    new PointLongY(0, 0),
                    new PointLongY(0, 1),
                    new PointLongY(1, 1),
                    new PointLongY(1, 0)))
    );
}
