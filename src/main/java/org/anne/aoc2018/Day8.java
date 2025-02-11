package org.anne.aoc2018;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day8 extends Day {
    public static void main(String[] args) {
        new Day8().run();
    }

    @Override
    public void execute() {
        setName("Memory Maneuver");
        List<Integer> input = readFileIntegerOneLine(" ");
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(List<Integer> input) {
        Node root = readNode(input, new AtomicInteger(0));
        return root.sum();
    }

    public static int part2(List<Integer> input) {
        Node root = readNode(input, new AtomicInteger(0));
        return root.value();
    }

    static Node readNode(List<Integer> numbers, AtomicInteger index) {
        int children = numbers.get(index.getAndIncrement());
        int metadata = numbers.get(index.getAndIncrement());
        List<Node> nodes = new ArrayList<>();
        List<Integer> metadataList = new ArrayList<>();

        for (int i = 0; i < children; i++) {
            nodes.add(readNode(numbers, index));
        }

        for (int i = 0; i < metadata; i++) {
            metadataList.add(numbers.get(index.getAndIncrement()));
        }

        return new Node(metadataList, nodes);
    }

    record Node(List<Integer> metadata, List<Node> nodes) {

        int sum() {
            int metadataSum = metadata.stream().mapToInt(Integer::intValue).sum();
            int nodesSum = nodes.stream().mapToInt(Node::sum).sum();
            return metadataSum + nodesSum;
        }

        int value() {
            if (nodes.isEmpty()) {
                return metadata.stream().mapToInt(Integer::intValue).sum();
            }

            return metadata.stream()
                    .filter(m -> m <= nodes.size())
                    .mapToInt(m -> nodes.get(m - 1).value())
                    .sum();
        }
    }
}
