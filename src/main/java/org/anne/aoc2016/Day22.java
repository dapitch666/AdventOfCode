package org.anne.aoc2016;

import org.anne.common.Day;
import org.anne.common.Direction;
import org.anne.common.Utils;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.IntStream;

public class Day22 extends Day {
    public static void main(String[] args) {
        new Day22().run();
    }

    @Override
    public void execute() {
        setName("Grid Computing");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(List<String> input) {
        List<Node> nodes = getNodes(input);
        return nodes.stream()
                .filter(node -> !node.isEmpty())
                .mapToInt(node -> (int) nodes.stream().filter(node::fitIn).count())
                .sum();
    }

    public static int part2(List<String> input) {
        List<Node> nodes = getNodes(input);
        Node goal = nodes.stream().filter(Node::isTopRow).max(Node::compareTo).orElseThrow();
        Node emptyNode = nodes.stream().filter(Node::isEmpty).findFirst().orElseThrow();
        Optional<Point> via = nodes.stream()
                .filter(node -> node.size > 500) // 500 is the min size that can't be moved
                .min(Node::compareTo)
                .map(n -> Direction.WEST.move(n.point));
        return emptyNode.distanceTo(goal, via) + 5 * (goal.point.x - 1);
    }

    private static List<Node> getNodes(List<String> input) {
        return input.stream()
                .skip(2)
                .map(Utils::inputToIntStream)
                .map(IntStream::toArray)
                .map(Node::new)
                .toList();
    }

    record Node(Point point, int size, int used) implements Comparable<Node> {
        Node(int[] ints) {
            this(new Point(ints[0], ints[1]), ints[2], ints[3]);
        }

        boolean isEmpty() {
            return used == 0;
        }

        boolean fitIn(Node other) {
            return !other.equals(this) && other.size > other.used + this.used;
        }

        boolean isTopRow() {
            return point.y == 0;
        }

        @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
        int distanceTo(Node other, Optional<Point> via) {
            return via.map(p -> Utils.manhattanDistance(this.point, p) + Utils.manhattanDistance(p, other.point))
                    .orElseGet(() -> Utils.manhattanDistance(this.point, other.point));
        }

        @Override
        public int compareTo(@NotNull Node other) {
            return Comparator.comparingInt((Node n) -> n.point.x).compare(this, other);
        }
    }
}