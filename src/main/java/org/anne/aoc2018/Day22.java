package org.anne.aoc2018;

import org.anne.common.Day;
import org.anne.common.Direction;
import org.anne.common.GridHelper;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Day22 extends Day {
    public static void main(String[] args) {
        new Day22().run();
    }

    @Override
    public void execute() {
        setName("Mode Maze");
        List<Integer> input = readFileGetAllInts();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(List<Integer> input) {
        Point target = new Point(input.get(1), input.get(2));
        int depth = input.get(0);
        return Arrays.stream(getGrid(target, depth, 0))
                .flatMapToInt(Arrays::stream)
                .map(value -> value % 3)
                .sum();
    }

    public static int part2(List<Integer> input) {
        Point target = new Point(input.get(1), input.get(2));
        int depth = input.get(0);
        int[][] grid = getGrid(target, depth, 20); // Adjust extra space to get the correct answer

        PriorityQueue<State> queue = new PriorityQueue<>(Comparator.comparingInt(s -> s.minutes));
        queue.add(new State(0, new Point(0, 0), Tool.TORCH));
        Set<State> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            State current = queue.poll();
            if (current.point.equals(target) && current.tool == Tool.TORCH) {
                return current.minutes;
            }
            if (!visited.add(current)) {
                continue;
            }
            for (Tool tool : Tool.values()) {
                if (tool != current.tool && tool != Region.fromErosion(GridHelper.get(current.point, grid)).forbidden) {
                    queue.add(new State(current.minutes + 7, current.point, tool));
                }
            }
            for (Direction dir : Direction.values()) {
                Point next = dir.move(current.point);
                if (GridHelper.isValidPoint(next, grid) && current.tool != Region.fromErosion(GridHelper.get(next, grid)).forbidden) {
                    queue.add(new State(current.minutes + 1, next, current.tool));
                }
            }
        }
        return 0;
    }

    private static int[][] getGrid(Point target, int depth, int extra) {
        int width = target.x + 1 + extra;
        int height = target.y + 1 + extra;
        int[][] grid = new int[height][width];
        Point start = new Point(0, 0);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Point point = new Point(x, y);
                int geo = getGeo(target, point, start, grid);
                GridHelper.set(point, grid, (geo + depth) % 20183);
            }
        }
        return grid;
    }

    private static int getGeo(Point target, Point point, Point start, int[][] grid) {
        if (point.equals(start) || point.equals(target)) return 0;
        if (point.x == 0 || point.y == 0) return point.x * 16807 + point.y * 48271;
        return GridHelper.get(Direction.WEST.move(point), grid) * GridHelper.get(Direction.NORTH.move(point), grid);
    }

    record State(int minutes, Point point, Tool tool) {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return point.equals(state.point) && tool == state.tool;
        }

        @Override
        public int hashCode() {
            return Objects.hash(point, tool);
        }
    }

    enum Tool {
        CLIMBING_GEAR, TORCH, NEITHER
    }

    enum Region {
        ROCKY(Tool.NEITHER),
        WET(Tool.TORCH),
        NARROW(Tool.CLIMBING_GEAR);

        final Tool forbidden;

        Region(Tool tool) {
            this.forbidden = tool;
        }

        static Region fromErosion(int erosion) {
            return switch (erosion % 3) {
                case 0 -> ROCKY;
                case 1 -> WET;
                case 2 -> NARROW;
                default -> throw new IllegalStateException("Unexpected value: " + erosion % 3);
            };
        }
    }
}
