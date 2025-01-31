package org.anne.aoc2017;

import org.anne.common.Day;

import java.awt.*;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Day11 extends Day {
    public static void main(String[] args) {
        new Day11().run();
    }

    @Override
    public void execute() {
        setName("Hex Ed");
        String input = readFileOneLine();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(String input) {
        Map<String, Integer> directions = Arrays.stream(input.split(","))
                .collect(Collectors.toMap(d -> d, d -> 1, Integer::sum));
        Point point = new Point(0, 0);
        directions.forEach((direction, count) -> move(direction, point, count));
        return getDistance(point);
    }

    public static int part2(String input) {
        int max = 0;
        Point point = new Point(0, 0);
        for (String direction : input.split(",")) {
            move(direction, point, 1);
            max = Math.max(max, getDistance(point));
        }
        return max;
    }

    private static int getDistance(Point point) {
        return (Math.abs(point.x) + Math.abs(point.y) + Math.abs(point.x + point.y)) / 2;
    }

    private static void move(String direction, Point point, int d) {
        switch (direction) {
            case "n" -> point.translate(0, d);
            case "ne" -> point.translate(d, 0);
            case "se" -> point.translate(d, -d);
            case "s" -> point.translate(0, -d);
            case "sw" -> point.translate(-d, 0);
            case "nw" -> point.translate(-d, d);
        }
    }
}
