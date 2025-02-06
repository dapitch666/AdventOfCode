package org.anne.aoc2016;

import org.anne.common.Day;
import org.anne.common.Direction;

import java.awt.*;
import java.util.*;

public class Day13 extends Day {
    public static void main(String[] args) {
        new Day13().run();
    }

    @Override
    public void execute() {
        setName("A Maze of Twisty Little Cubicles");
        int input = Integer.parseInt(readFileOneLine());
        setPart1(part1(input, 31, 39));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(int input, int x, int y) {
        return findPath(input).get(new Point(x, y));
    }

    public static long part2(int input) {
        return findPath(input).values().stream().filter(steps -> steps <= 50).count();
    }

    private static Map<Point, Integer> findPath(int favoriteNumber) {
        Deque<State> path = new ArrayDeque<>();
        path.add(new State(new Point(1, 1), 0));
        Map<Point, Integer> visited = new HashMap<>();

        while (!path.isEmpty()) {
            State current = path.removeLast();
            visited.put(current.point, current.steps);
            for (Direction direction : Direction.values()) {
                Point neighbor = direction.move(current.point);
                if (isWalkable(favoriteNumber, neighbor) && (!visited.containsKey(neighbor) || visited.get(neighbor) > current.steps + 1)) {
                    path.add(new State(neighbor, current.steps + 1));
                }
            }
        }
        return visited;
    }

    static boolean isWalkable(int favoriteNumber, Point point) {
        int x = point.x;
        int y = point.y;
        if (x < 0 || y < 0) {
            return false;
        }
        int sum = x * x + 3 * x + 2 * x * y + y + y * y + favoriteNumber;
        return Integer.bitCount(sum) % 2 == 0;
    }

    record State(Point point, int steps) {}
}