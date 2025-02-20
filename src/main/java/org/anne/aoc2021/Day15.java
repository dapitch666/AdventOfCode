package org.anne.aoc2021;

import org.anne.common.Day;
import org.anne.common.Direction;
import org.anne.common.GridHelper;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Day15 extends Day {

    public static void main(String[] args) {
        new Day15().run();
    }

    @Override
    public void execute() {
        setName("Chiton");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static long part1(List<String> input) {
        List<List<Node>> mapInput = parseInput(input, 1);
        return calculateLowerRiskPathCost(mapInput);
    }

    public static long part2(List<String> input) {
        List<List<Node>> mapInput = parseInput(input, 5);
        return calculateLowerRiskPathCost(mapInput);
    }

    private static long calculateLowerRiskPathCost(List<List<Node>> nodes) {
        PriorityQueue<QueueEntry> queue = new PriorityQueue<>();
        queue.offer(new QueueEntry(new Point(), 0));
        nodes.getFirst().getFirst().costFromStart = 0;

        while (!queue.isEmpty()) {
            QueueEntry current = queue.poll();
            for (Direction direction : Direction.values()) {
                tryAddNextPathNode(direction, current, nodes, queue);
            }
        }

        return nodes.getLast().get(nodes.getFirst().size()-1).costFromStart;
    }

    private static void tryAddNextPathNode(Direction direction, Point previous,
                                           List<List<Node>> nodes, PriorityQueue<QueueEntry> queue) {

        Point next = direction.move(previous);
        if (next.x < 0 || next.x >= nodes.getFirst().size() || next.y < 0 || next.y >= nodes.size()) return;

        Node node = nodes.get(next.y).get(next.x);
        if (node.costFromStart != Long.MAX_VALUE) return;

        Node prevNode = nodes.get(previous.y).get(previous.x);
        long newCost = node.cost + prevNode.costFromStart;

        if (newCost < node.costFromStart) {
            node.costFromStart = newCost;
            node.prevNode = previous;
        }

        queue.offer(new QueueEntry(next, node.costFromStart));
    }

    private static List<List<Node>> parseInput(List<String> input, int multiplier) {
        int[][] intGrid = GridHelper.getIntGrid(input);
        int height = intGrid.length;
        int width = intGrid[0].length;

        List<List<Node>> mapInput = new ArrayList<>();
        for (int y = 0; y < height * multiplier; y++) {
            mapInput.add(new ArrayList<>());
            for (int x = 0; x < width * multiplier; x++) {
                int value = intGrid[y % height][x % width] + (multiplier > 1 ? x / width + y / height : 0);
                if (value >= 10)
                    value -= 9;
                mapInput.get(y).add(new Node(value));
            }
        }
        return mapInput;
    }

    private static class Node {
        final int cost;
        long costFromStart;
        Point prevNode;

        Node(int cost) {
            this.cost = cost;
            this.costFromStart = Long.MAX_VALUE;
            this.prevNode = new Point();
        }
    }

    private static class QueueEntry extends Point implements Comparable<QueueEntry> {
        final long priority;

        QueueEntry(Point point, long priority) {
            super(point);
            this.priority = priority;
        }

        @Override
        public int compareTo(QueueEntry other) {
            return Long.compare(this.priority, other.priority);
        }
    }
}