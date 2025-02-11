package org.anne.aoc2018;

import org.anne.common.Day;
import org.anne.common.Pair;
import org.anne.common.Utils;

import java.awt.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day10 extends Day {
    public static void main(String[] args) {
        new Day10().run();
    }

    @Override
    public void execute() {
        setName("The Stars Align");
        List<String> input = readFile();
        setPart1(part1(input, 10));
        setPart2(part2(input, 10));
    }

    public static String part1(List<String> input, int maxHeight) {
        List<Pair<Point>> points = parseInput(input);
        while (true) {
            points.forEach(point -> point.first().translate(point.second().x, point.second().y));
            int minY = points.stream().mapToInt(p -> p.first().y).min().orElseThrow();
            int maxY = points.stream().mapToInt(p -> p.first().y).max().orElseThrow();
            if (maxY - minY < maxHeight) {
                if (maxHeight == 10) {
                    // System.out.println(Utils.printAscii(points.stream().map(Pair::first).collect(Collectors.toSet())));
                    return Utils.ocr(Utils.getArray(points.stream().map(Pair::first).collect(Collectors.toSet())), maxHeight - 2, maxHeight);
                } else {
                    return Utils.printAscii(points.stream().map(Pair::first).collect(Collectors.toSet()), " ", "#");
                }
            }
        }
    }

    public static int part2(List<String> input, int maxHeight) {
        List<Pair<Point>> points = parseInput(input);
        int seconds = 0;
        while (true) {
            points.forEach(point -> point.first().translate(point.second().x, point.second().y));
            seconds++;
            int minY = points.stream().mapToInt(p -> p.first().y).min().orElseThrow();
            int maxY = points.stream().mapToInt(p -> p.first().y).max().orElseThrow();
            if (maxY - minY < maxHeight) {
                return seconds;
            }
        }
    }

    private static List<Pair<Point>> parseInput(List<String> input) {
        Pattern pattern = Pattern.compile("position=<\\s*(-?\\d+),\\s*(-?\\d+)> velocity=<\\s*(-?\\d+),\\s*(-?\\d+)>");
        return input.stream()
                .map(pattern::matcher)
                .filter(Matcher::find)
                .map(matcher -> new Pair<>(
                        new Point(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))),
                        new Point(Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)))))
                .collect(Collectors.toList());
    }
}
