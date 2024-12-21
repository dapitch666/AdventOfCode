package org.anne.aoc2023;

import org.anne.common.Day;
import org.anne.common.GridHelper;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day23 extends Day {
    public static void main(String[] args) {
        Day day = new Day23();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("A Long Walk");
        List<String> input = this.readFile();
        this.setPart1(part1(input));
        this.setPart2(part2(input));
        this.printParts();
    }
    
    static final Set<Point> visited = new HashSet<>();
    static final Map<Point, Map<Point, Integer>> graphs = new HashMap<>();

    public static int part1(List<String> input) {
        return longestPath(input, true);
    }

    public static int part2(List<String> input) {
        return longestPath(input, false);
    }
    
    public static int longestPath(List<String> input, boolean slipperySlopes) {
        var start = new Point(1, 0);
        var end = new Point(input.get(0).length() - 2, input.size() - 1);
        var grid = GridHelper.getCharGrid(input);
        var gridSize = grid.length;
        var points = new HashSet<Point>();
        points.add(start);
        points.add(end);
        for (var y = 0; y < gridSize; y++) {
            for (var x = 0; x < gridSize; x++) {
                if (grid[y][x] != '#') {
                    var numNeighbours = Stream.of(
                            new Point(x + 1, y),
                            new Point(x - 1, y),
                            new Point(x, y + 1),
                            new Point(x, y - 1))
                            .filter(p -> GridHelper.isValidPoint(p, gridSize))
                            .filter(p -> grid[p.y][p.x] != '#')
                            .count();
                    if (numNeighbours >= 3) {
                        points.add(new Point(x, y));
                    }
                }
            }
        }
        
        for (var point : points) { 
            var stack = new ArrayDeque<Point>();
            var stackMap = new HashMap<Point, Integer>();
            var seen = new HashSet<Point>();
            stack.add(point);
            stackMap.put(point, 0);
            seen.add(point);
            while (!stack.isEmpty()) {
                var currentPoint = stack.pop();
                var currentDistance = stackMap.get(currentPoint);
                if (currentDistance != 0 && points.contains(currentPoint)) {
                    graphs.computeIfAbsent(point, p -> new HashMap<>())
                            .put(currentPoint, currentDistance);
                    continue;
                }
                var neighbours = neighbours(List.of(currentPoint), grid, slipperySlopes)
                        .stream()
                        .filter(p -> !seen.contains(p))
                        .toList();
                for (var neighbour : neighbours) {
                    stack.add(neighbour);
                    stackMap.put(neighbour, currentDistance + 1);
                    seen.add(neighbour);
                }
            }
        }
        return dfs(start, end);
    }
    
    static int dfs(Point p, Point end) {
        if (p.equals(end)) {
            return 0;
        }
        var max = Integer.MIN_VALUE;
        visited.add(p);
        for (var graph : graphs.get(p).entrySet()) {
            if (!visited.contains(graph.getKey())) {
                max = Math.max(max, graph.getValue() + dfs(graph.getKey(), end));
            }
        }
        visited.remove(p);
        return max;
    }
    
    static List<Point> neighbours(List<Point> path, char[][] grid, boolean slipperySlopes) {
        var p = path.get(path.size() - 1);
        List<Point> points;
        if (slipperySlopes) {
            points = switch (grid[p.y][p.x]) {
                case '>' -> List.of(new Point(p.x + 1, p.y));
                case '<' -> List.of(new Point(p.x - 1, p.y));
                case '^' -> List.of(new Point(p.x, p.y - 1));
                case 'v' -> List.of(new Point(p.x, p.y + 1));
                default -> List.of(
                        new Point(p.x + 1, p.y),
                        new Point(p.x - 1, p.y),
                        new Point(p.x, p.y + 1),
                        new Point(p.x, p.y - 1));
            };
        } else {
            points = List.of(
                    new Point(p.x + 1, p.y),
                    new Point(p.x - 1, p.y),
                    new Point(p.x, p.y + 1),
                    new Point(p.x, p.y - 1));
        }
        return points.stream()
                .filter(p2 -> GridHelper.isValidPoint(p2, grid))
                .filter(p2 -> grid[p2.y][p2.x] != '#')
                .filter(p2 -> !path.contains(p2))
                .collect(Collectors.toList());
    }
}
