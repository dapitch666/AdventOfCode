package org.anne.aoc2024;

import org.anne.common.Day;
import org.anne.common.Direction;

import java.awt.*;
import java.util.HashSet;
import java.util.List;

import static org.anne.common.Direction.getPoint;

public class Day6 extends Day {
    public static void main(String[] args) {
        Day day = new Day6();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Guard Gallivant");
        List<String> input = this.readFile();
        this.setPart1(part1(input));
        this.setPart2(part2(input));
        this.printParts();
    }

    public static int part1(List<String> input) {
        char[][] map = getMap(input);
        Point start = getStart(input);
        return getPatrolPath(start, map).size();
    }

    public static int part2(List<String> input) {
        char[][] map = getMap(input);
        Point start = getStart(input);
        HashSet<Point> visited = getPatrolPath(start, map);
        int loop = 0;
        for (Point p : visited) {
            HashSet<State> path = new HashSet<>();
            Point current = start;
            Direction direction = Direction.NORTH;
            path.add(new State(current, direction));
            boolean outOfBounds = false;
            while (!outOfBounds) {
                Point next = getPoint(direction, current);
                try {
                    if (map[next.y][next.x] == '#' || next.equals(p)) {
                        direction = Direction.rotate90(direction, true);
                        if (!path.add(new State(current, direction))) {
                            loop++;
                            break;
                        }
                    } else {
                        current = next;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    outOfBounds = true;
                }
            }
        }
        return loop;
    }

    private static Point getStart(List<String> input) {
        return input.stream()
                .map(line -> new Point(line.indexOf('^'), input.indexOf(line)))
                .filter(point -> point.x != -1)
                .findFirst()
                .orElse(null);
    }

    private static char[][] getMap(List<String> input) {
        return input.stream().map(String::toCharArray).toArray(char[][]::new);
    }

    private static HashSet<Point> getPatrolPath(Point start, char[][] map) {
        HashSet<Point> visited = new HashSet<>();
        visited.add(start);
        Point current = start;
        Direction direction = Direction.NORTH;
        boolean outOfBounds = false;
        while (!outOfBounds) {
            Point next = getPoint(direction, current);
            try {
                if (map[next.y][next.x] == '#') {
                    direction = Direction.rotate90(direction, true);
                } else {
                    visited.add(next);
                    current = next;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                outOfBounds = true;
            }
        }
        return visited;
    }

    record State(Point position, Direction direction) {
    }
}