package org.anne.aoc2023;

import org.anne.common.Day;
import org.anne.common.Point3d;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class Day22 extends Day {
    public static void main(String[] args) {
        Day day = new Day22();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Sand Slabs");
        List<String> input = this.readFile();
        this.setPart1(part1(input));
        this.setPart2(part2(input));
        this.printParts();
    }

    public static long part1(List<String> input) {
        var bricks = getBricks(input);
        int[] countBricksDependencies = new int[bricks.size()];
        for (var brick : bricks.stream().filter(b -> b.isSupportedBy.size() == 1).toList()) {
            var i = bricks.indexOf(brick.isSupportedBy.stream().findFirst().orElseThrow());
            countBricksDependencies[i]++;
        }
        return Arrays.stream(countBricksDependencies).filter(i -> i == 0).count();
    }

    public static int part2(List<String> input) {
        var bricks = getBricks(input);
        var result = 0;
        for (Brick brick : bricks) {
            var removed = new HashSet<Brick>();
            removed.add(brick);
            var supported = brick.supports;
            while (!supported.isEmpty()) {
                supported = supported.stream()
                        .filter(supportedBrick -> removed.containsAll(supportedBrick.isSupportedBy))
                        .peek(removed::add)
                        .flatMap(supportedBrick -> supportedBrick.supports.stream())
                        .collect(Collectors.toSet());
            }
            result += removed.size() - 1;
        }
        return result;
    }

    static List<Brick> getBricks(List<String> input) {
        var bricks = input.stream().map(Brick::new).sorted().toList();
        var occupied = new HashMap<Point3d, Integer>();

        for (int i = 0; i < bricks.size(); i++) {
            occupied.putAll(bricks.get(i).getOccupied(i));
        }

        for (int i = 0; i < bricks.size(); i++) {
            var brick = bricks.get(i);
            boolean supported = brick.start.z() == 1;
            while(!supported) {
                supported = brick.start.z() == 1;
                if (brick.start.z() > 1) {
                    var below = brick.pointsBelow().stream().filter(occupied::containsKey).toList();
                    if (!below.isEmpty()) {
                        supported = true;
                        for (var point : below) {
                            var brickBelow = bricks.get(occupied.get(point));
                            brickBelow.supports.add(brick);
                            brick.isSupportedBy.add(brickBelow);
                        }
                    }
                    if (!supported) {
                        occupied.keySet().removeAll(brick.getOccupied(i).keySet());
                        brick.drop();
                        occupied.putAll(brick.getOccupied(i));
                    }
                }
            }
        }
        return bricks;
    }

    static class Brick implements Comparable<Brick> {
        Point3d start;
        int size;
        int direction;
        final Set<Brick> supports = new HashSet<>();
        final Set<Brick> isSupportedBy = new HashSet<>();

        final static int[] xs = new int[]{1, 0, 0};
        final static int[] ys = new int[]{0, 1, 0};
        final static int[] zs = new int[]{0, 0, 1};

        public Brick (String s) {
            String[] split = s.split("~");
            String[] p1 = split[0].split(",");
            String[] p2 = split[1].split(",");
            this.start = new Point3d(Integer.parseInt(p1[0]), Integer.parseInt(p1[1]), Integer.parseInt(p1[2]));
            var end = new Point3d(Integer.parseInt(p2[0]), Integer.parseInt(p2[1]), Integer.parseInt(p2[2]));
            if (this.start.x() != end.x()) {
                direction = 0;
                size = Math.abs(this.start.x() - end.x());
            } else if (this.start.y() != end.y()) {
                direction = 1;
                size = Math.abs(this.start.y() - end.y());
            } else if (this.start.z() != end.z()) {
                direction = 2;
                size = Math.abs(this.start.z() - end.z());
            }
            size++;
        }

        @Override
        public int compareTo(@NotNull Day22.Brick o) {
            return Integer.compare(start.z(), o.start.z());
        }

        public Map<Point3d, Integer> getOccupied(int id) {
            var occupied = new HashMap<Point3d, Integer>();
            for (int i = 0; i < this.size; i++) {
                var x = this.start.x() + i * xs[this.direction];
                var y = this.start.y() + i * ys[this.direction];
                var z = this.start.z() + i * zs[this.direction];
                occupied.put(new Point3d(x, y, z), id);
            }
            return occupied;
        }

        public List<Point3d> pointsBelow() {
            if (this.direction == 2) {
                return List.of(this.start.add(new Point3d(0, 0, -1)));
            } else {
                var points = new ArrayList<Point3d>();
                for (int i = 0; i < this.size; i++) {
                    var x = this.start.x() + i * xs[this.direction];
                    var y = this.start.y() + i * ys[this.direction];
                    var z = this.start.z() - 1;
                    points.add(new Point3d(x, y, z));
                }
                return points;
            }
        }

        public void drop() {
            this.start = this.start.add(new Point3d(0, 0, -1));
        }
    }
}
