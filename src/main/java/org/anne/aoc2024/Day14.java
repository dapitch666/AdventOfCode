package org.anne.aoc2024;

import org.anne.common.Day;
// import org.anne.common.Utils;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day14 extends Day {
    public static void main(String[] args) {
        Day day = new Day14();
        day.setName("Restroom Redoubt");
        List<String> input = day.readFile();
        day.setPart1(part1(input, 101, 103));
        day.setPart2(part2(input));
        day.printParts();
    }

    public static int part1(List<String> input, int width, int height) {
        List<Integer> quadrants = Arrays.asList(0, 0, 0, 0);
        List<Robot> robots = input.stream().map(Robot::from).toList();
        for (Robot robot : robots) {
            Point finalPoint = robot.move(100, width, height);
            if (finalPoint.x < width / 2 && finalPoint.y < height / 2) {
                quadrants.set(0, quadrants.get(0) + 1);
            } else if (finalPoint.x > width / 2 && finalPoint.y < height / 2) {
                quadrants.set(1, quadrants.get(1) + 1);
            } else if (finalPoint.x < width / 2 && finalPoint.y > height / 2) {
                quadrants.set(2, quadrants.get(2) + 1);
            } else if (finalPoint.x > width / 2 && finalPoint.y > height / 2) {
                quadrants.set(3, quadrants.get(3) + 1);
            }
        }
        return quadrants.get(0) * quadrants.get(1) * quadrants.get(2) * quadrants.get(3);
    }

    public static int part2(List<String> input) {
        List<Robot> robots = input.stream().map(Robot::from).toList();
        int nbRobots = robots.size();
        int i = 0;
        while (true) {
            int finalI = i;
            Set<Point> points = robots.stream().map(r -> r.move(finalI, 101, 103)).collect(Collectors.toSet());
            // The assumption is that the Christmas tree is visible when all robots are in distinct positions, and it worked \o/
            if (points.size() == nbRobots) {
                // System.out.println(Utils.printAscii(points));
                break;
            }
            i++;
        }
        return i;
    }

    record Robot(int x, int y, int vx, int vy) {
        static Robot from(String line) {
            var matcher = Pattern.compile("p=(\\d+),(\\d+) v=(-?\\d+),(-?\\d+)").matcher(line);
            if (!matcher.matches()) {
                throw new IllegalArgumentException(line);
            }
            return new Robot(
                    Integer.parseInt(matcher.group(1)),
                    Integer.parseInt(matcher.group(2)),
                    Integer.parseInt(matcher.group(3)),
                    Integer.parseInt(matcher.group(4))
            );
        }

        Point move(int times, int width, int height) {
            int x = (this.x + times * vx) % width;
            int y = (this.y + times * vy) % height;
            return new Point(x < 0 ? x + width : x, y < 0 ? y + height : y);
        }
    }
}
