package org.anne.aoc2021;

import org.anne.common.Day;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Day5 extends Day {

    public static void main(String[] args) {
        new Day5().run();
    }

    @Override
    public void execute() {
        setName("Hydrothermal Venture");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static long part1(List<String> input) {
        return countOverlap(input, 1);
    }

    public static long part2(List<String> input) {
        return countOverlap(input, 2);
    }

    private static List<Point> getAllPoints(String line, int part) {
        List<Point> points = new ArrayList<>();
        Pattern pattern = Pattern.compile("(\\d+),(\\d+) -> (\\d+),(\\d+)");
        Matcher matcher = pattern.matcher(line);
        Point p1 = new Point();
        Point p2 = new Point();
        while (matcher.find()) {
            p1 = new Point(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
            p2 = new Point(Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));
        }
        if (p1.x == p2.x) {
            for (int i = min(p1.y, p2.y); i <= max(p1.y, p2.y); i++) {
                points.add(new Point(p1.x, i));
            }
        } else if (p1.y == p2.y) {
            for (int i = min(p1.x, p2.x); i <= max(p1.x, p2.x); i++) {
                points.add(new Point(i, p1.y));
            }
        } else if (part == 2) {
            Point pA, pB;
            if (p1.x < p2.x) {
                pA = p1;
                pB = p2;
            } else {
                pA = p2;
                pB = p1;
            }
            for (int i = 0; i <= (pB.x - pA.x); i++) {
                if (pA.y < pB.y) {
                    points.add(new Point(pA.x + i, pA.y + i));
                } else {
                    points.add(new Point(pA.x + i, pA.y - i));
                }
            }
        }
        return points;
    }

    private static long countOverlap(List<String> input, int part) {
        Map<Point, Integer> points = new HashMap<>();
        for (String line : input) {
            List<Point> newPoints = getAllPoints(line, part);
            for (Point p : newPoints) {
                int count = points.getOrDefault(p, 0);
                points.put(p, count + 1);
            }

        }
        return points.values()
                .stream()
                .filter(i -> i >= 2)
                .count();
    }
}
