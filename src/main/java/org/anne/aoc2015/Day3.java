package org.anne.aoc2015;

import org.anne.common.Day;
import org.anne.common.Direction;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day3 extends Day {
    public static void main(String[] args) {
        new Day3().run();
    }

    @Override
    public void execute() {
        setName("Perfectly Spherical Houses in a Vacuum");
        String input = readFileOneLine();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(String input) {
        Set<Point> visited = new HashSet<>();
        Point current = new Point();
        visited.add(current);
        for (char c : input.toCharArray()) {
            current = Direction.fromChar(c).move(current);
            visited.add(current);
        }
        return visited.size();
    }

    public static int part2(String input) {
        Set<Point> visited = new HashSet<>();
        Point santa = new Point(), robot = new Point();
        visited.add(santa);
        for (int i = 0; i < input.length(); i += 2) {
            santa = Direction.fromChar(input.charAt(i)).move(santa);
            robot = Direction.fromChar(input.charAt(i+1)).move(robot);
            visited.add(santa);
            visited.add(robot);
        }
        return visited.size();
    }
}
