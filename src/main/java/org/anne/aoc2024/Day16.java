package org.anne.aoc2024;

import org.anne.common.Day;
import org.anne.common.Direction;
import org.anne.common.GridHelper;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Day16 extends Day {

    public static void main(String[] args) {
        new Day16().run();
    }

    @Override
    public void execute() {
        setName("Reindeer Maze");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(List<String> input) {
        char[][] maze = GridHelper.getCharGrid(input);
        int i = input.size() - 2;
        Position start = new Position(new Point(1, i), Direction.EAST);
        Point end = new Point(i, 1);

        Map<Position, Integer> seen = new HashMap<>();
        seen.put(start, 0);
        int minScore = Integer.MAX_VALUE;

        Queue<State> queue = new PriorityQueue<>(Comparator.comparing(State::score));
        queue.add(new State(start, 0, new HashSet<>(List.of(start.point))));

        while (!queue.isEmpty()) {
            State state = queue.poll();
            for (State next : state.nextStates()) {
                if (next.score >= minScore) {
                    break;
                }
                if (maze[next.position.point.y][next.position.point.x] != '#'
                        && next.score < seen.getOrDefault(next.position, Integer.MAX_VALUE)) {
                    queue.add(next);
                    seen.put(next.position, next.score);
                    if (end.equals(next.position.point)) {
                        minScore = next.score;
                    }
                }
            }
        }
        return minScore;
    }

    public static int part2(List<String> input) {
        char[][] maze = GridHelper.getCharGrid(input);
        int i = input.size() - 2;
        Position start = new Position(new Point(1, i), Direction.EAST);
        Point end = new Point(i, 1);

        Set<Point> bestSpots = new HashSet<>();
        Map<Position, Integer> seen = new HashMap<>();
        seen.put(start, 0);
        int minScore = Integer.MAX_VALUE;

        Queue<State> queue = new PriorityQueue<>(Comparator.comparing(State::score));
        Map<Point, Set<Point>> paths = new HashMap<>();

        queue.add(new State(start, 0, new HashSet<>(List.of(start.point))));
        while (!queue.isEmpty()) {
            State state = queue.poll();
            for (State next : state.nextStates()) {
                if (next.score > minScore) {
                    break;
                }
                if (maze[next.position.point.y][next.position.point.x] != '#') {
                    int score = seen.getOrDefault(next.position, Integer.MAX_VALUE);
                    if (next.score < score) {
                        queue.add(next);
                        seen.put(next.position, next.score);
                        if (end.equals(next.position.point)) {
                            minScore = next.score;
                            bestSpots.addAll(next.visited);
                        }
                        paths.put(next.position.point, next.visited);
                    } else if (next.score == score) {
                        paths.get(next.position.point).addAll(next.visited);
                    }
                }
            }
        }
        return bestSpots.size();
    }

    record Position(Point point, Direction direction) {
    }

    record State(Position position, int score, Set<Point> visited) {
        List<State> nextStates() {
            Point nextPoint = position.direction.move(position.point);
            Set<Point> newVisited = new HashSet<>(visited);
            newVisited.add(nextPoint);
            return List.of(
                    new State(new Position(position.point, position.direction.rotate90(false)),
                            score + 1000, visited),
                    new State(new Position(position.point, position.direction.rotate90(true)),
                            score + 1000, visited),
                    new State(new Position(nextPoint, position.direction),
                            score + 1, newVisited)
            );
        }
    }
}