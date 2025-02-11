package org.anne.aoc2017;

import org.anne.common.Day;
import org.anne.common.Direction;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day3 extends Day {
    public static void main(String[] args) {
        new Day3().run();
    }

    @Override
    public void execute() {
        setName("Spiral Memory");
        int input = Integer.parseInt(readFileOneLine());
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(int input) {
        int size = (int) Math.ceil(Math.sqrt(input));
        int center = (int) Math.ceil((size - 1) / 2.0);
        return Math.max(0, center - 1 + Math.abs(center - input % size));
    }

    public static int part2(int input) {
        Point point = new Point(0, 0);
        Map<Point, Integer> spiral = new HashMap<>();
        spiral.put(point, 1);

        while (true) {
            point = nextPoint(point);
            int value = 0;
            for (int y = point.y - 1; y <= point.y + 1; y++) {
                for (int x = point.x - 1; x <= point.x + 1; x++) {
                    value += spiral.getOrDefault(new Point(x, y), 0);
                }
            }
            if (value > input) {
                return value;
            }
            spiral.put(point, value);
        }
    }

    private static Point nextPoint(Point point) {
        boolean moveHorizontally = (point.x != point.y || point.x >= 0) && Math.abs(point.x) <= Math.abs(point.y);
        if (moveHorizontally) {
            return point.y >= 0 ? Direction.EAST.move(point) : Direction.WEST.move(point);
        } else {
            return point.x >= 0 ? Direction.NORTH.move(point) : Direction.SOUTH.move(point);
        }
    }

    private static int getValue(Map<Point, Integer> spiral, Point point) {
        int sum = 0;
        for (int y = point.y - 1; y <= point.y + 1; y++) {
            for (int x = point.x - 1; x <= point.x + 1; x++) {
                sum += spiral.getOrDefault(new Point(x, y), 0);
            }
        }
        return sum;
    }
}
