package org.anne.aoc2022;

import org.anne.common.Day;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day9 extends Day {
    public static void main(String[] args) {
        Day day = new Day9();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Rope Bridge");
        List<String> input = this.readFile();
        this.setPart1(part1(input));
        this.setPart2(part2(input));
        this.printParts();
    }

    public static int part1(List<String> input) {
        return moveRope(input, 2);
    }

    public static int part2(List<String> input) {
        return moveRope(input, 10);
    }

    public static int moveRope(List<String> input, int numberOfKnots) {
        Set<Point> visited = new HashSet<>();
        Point[] knots = new Point[numberOfKnots];
        for (int i = 0; i < numberOfKnots; i++) {
            knots[i] = new Point(0, 0);
        }
        for (String line : input) {
            char direction = line.charAt(0);
            int moves = Integer.parseInt(line.substring(2));
            for (int i = 0; i < moves; i++) {
                switch (direction) {
                    case 'R' -> knots[0].x += 1;
                    case 'L' -> knots[0].x -= 1;
                    case 'U' -> knots[0].y += 1;
                    case 'D' -> knots[0].y -= 1;
                }
                for (int j = 1; j < numberOfKnots; j++) {
                    if (Math.abs(knots[j-1].x - knots[j].x) > 1 || Math.abs(knots[j-1].y - knots[j].y) > 1) {
                        int x = knots[j-1].x - knots[j].x;
                        int y = knots[j-1].y - knots[j].y;
                        knots[j] = new Point(
                                knots[j].x + (x == 0 ? 0 : (x / Math.abs(x))),
                                knots[j].y + (y == 0 ? 0 : (y / Math.abs(y))));
                    }
                }
                visited.add(knots[numberOfKnots-1]);
            }
        }
        return visited.size();
    }
}
