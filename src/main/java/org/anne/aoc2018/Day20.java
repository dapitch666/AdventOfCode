package org.anne.aoc2018;

import org.anne.common.Day;
import org.anne.common.Direction;

import java.awt.*;
import java.util.*;

public class Day20 extends Day {
    public static void main(String[] args) {
        new Day20().run();
    }

    @Override
    public void execute() {
        setName("A Regular Map");
        String input = readFileOneLine();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(String input) {
        var graph = getGraph(input.substring(1, input.length() - 1));
        return bfs(graph, false);
    }


    public static int part2(String input) {
        var graph = getGraph(input.substring(1, input.length() - 1));
        return bfs(graph, true);
    }

    private static int bfs(Map<Point, Set<Direction>> graph, boolean isPart2) {
        int result = 0;
        Deque<PointDistance> queue = new ArrayDeque<>();
        Set<Point> visited = new HashSet<>();
        Point start = new Point(0, 0);
        queue.add(new PointDistance(start, 0));
        visited.add(start);

        while (!queue.isEmpty()) {
            PointDistance pd = queue.poll();

            if (!isPart2) {
                result = Math.max(result, pd.distance);
            } else if (pd.distance >= 1000) {
                result++;
            }

            for (Direction direction : graph.getOrDefault(pd.point, Collections.emptySet())) {
                Point next = direction.move(pd.point);
                if (visited.add(next)) {
                    queue.add(new PointDistance(next, pd.distance + 1));
                }
            }
        }
        return result;
    }

    private static Map<Point, Set<Direction>> getGraph(String input) {
        Map<Point, Set<Direction>> graph = new HashMap<>();
        Stack<Point> stack = new Stack<>();
        Point point = new Point(0, 0);
        stack.push(point);

        for (char c : input.toCharArray()) {
            switch (c) {
                case 'N', 'S', 'W', 'E' -> point = addToGraph(c, graph, point);
                case '(' -> stack.push(point);
                case ')' -> point = stack.pop();
                case '|' -> point = stack.peek();
                default -> throw new IllegalArgumentException("Unexpected value: " + c);
            }
        }
        return graph;
    }

    private static Point addToGraph(char c, Map<Point, Set<Direction>> graph, Point point) {
        Direction direction = Direction.fromInitial(c);
        graph.computeIfAbsent(point, k -> new HashSet<>()).add(direction);
        point = direction.move(point);
        graph.computeIfAbsent(point, k -> new HashSet<>()).add(direction.reverse());
        return point;
    }

    record PointDistance(Point point, int distance) {
    }

}
