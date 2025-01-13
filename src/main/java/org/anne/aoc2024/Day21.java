package org.anne.aoc2024;

import org.anne.common.Day;
import org.anne.common.Direction;
import org.anne.common.GridHelper;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Day21 extends Day {
    public static void main(String[] args) {
        new Day21().run();
    }

    @Override
    public void execute() {
        setName("Keypad Conundrum");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static long part1(List<String> input) {
        return getComplexity(input, 2);
    }

    public static long part2(List<String> input) {
        return getComplexity(input, 25);
    }

    static long getComplexity(List<String> input, int robots) {
        final char[][] keypad = {
                {'#', '#', '#', '#', '#'},
                {'#', '7', '8', '9', '#'},
                {'#', '4', '5', '6', '#'},
                {'#', '1', '2', '3', '#'},
                {'#', '#', '0', 'A', '#'},
                {'#', '#', '#', '#', '#'}
        };
        long totalComplexity = 0;
        for (var code : input) {
            totalComplexity += Integer.parseInt(code.substring(0, 3)) * execute(keypad, code, robots, new HashMap<>());
        }
        return totalComplexity;
    }

    static long execute(char[][] grid, String code, int robots, Map<String, Long> memo) {
        final char[][] keypad = {
                {'#', '#', '#', '#', '#'},
                {'#', '#', '^', 'A', '#'},
                {'#', '<', 'v', '>', '#'},
                {'#', '#', '#', '#', '#'}
        };

        String key = code + "_" + robots;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        char curPos = 'A';
        long length = 0L;

        for (char nextPos : code.toCharArray()) {
            List<String> paths = findAllMinPaths(grid, curPos, nextPos);
            if (robots == 0) {
                length += paths.getFirst().length();
            } else {
                long minPathLength = Long.MAX_VALUE;
                for (String path : paths) {
                    minPathLength = Math.min(minPathLength, execute(keypad, path, robots - 1, memo));
                }
                length += minPathLength;
            }
            curPos = nextPos;
        }

        memo.put(key, length);
        return length;
    }

    static List<String> findAllMinPaths(char[][] grid, char s, char e) {
        Point start = GridHelper.findChar(grid, s);
        Point end = GridHelper.findChar(grid, e);
        Queue<Node> queue = new LinkedList<>(List.of(new Node(start, new ArrayList<>(), 0)));
        Map<Point, Integer> seen = new HashMap<>();
        List<List<Character>> paths = new ArrayList<>();
        int minCost = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            Point curPoint = curNode.p;
            if (curNode.direction != null) {
                curNode.path.add(curNode.direction.getChar());
            }
            if (curPoint.equals(end)) {
                if (curNode.cost < minCost) {
                    paths.clear();
                    minCost = curNode.cost;
                }
                if (curNode.cost == minCost) {
                    paths.add(new ArrayList<>(curNode.path));
                }
                continue;
            }

            if (seen.getOrDefault(curPoint, Integer.MAX_VALUE) < curNode.cost) {
                continue;
            }
            seen.put(curPoint, curNode.cost);
            if (curNode.cost > minCost) {
                continue;
            }

            for (Direction direction : Direction.values()) {
                Point nextPoint = direction.move(curPoint);
                if (grid[nextPoint.y][nextPoint.x] != '#') {
                    queue.add(new Node(nextPoint, new ArrayList<>(curNode.path), curNode.cost + 1, direction));
                }
            }
        }

        List<String> result = new ArrayList<>();
        for (List<Character> path : paths) {
            StringBuilder sb = new StringBuilder();
            for (char c : path) {
                sb.append(c);
            }
            sb.append('A');
            result.add(sb.toString());
        }
        return result;
    }

    record Node (Point p, List<Character> path, int cost, Direction direction) {
        Node(Point p, List<Character> path, int cost) {
            this(p, path, cost, null);
        }
    }
}