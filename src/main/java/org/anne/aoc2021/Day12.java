package org.anne.aoc2021;

import org.anne.common.Day;

import java.util.*;

public class Day12 extends Day {

    public static void main(String[] args) {
        new Day12().run();
    }

    @Override
    public void execute() {
        setName("Passage Pathing");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    static long part1(List<String> input) {
        HashMap<String, Set<String>> connections = buildConnections(input);
        Stack<String> path = new Stack<>();
        path.push("start");
        return pathFinder(path, connections, false);
    }

    static long part2(List<String> input) {
        HashMap<String, Set<String>> connections = buildConnections(input);
        Stack<String> path = new Stack<>();
        path.push("start");
        return pathFinder(path, connections, true);
    }

    static HashMap<String, Set<String>> buildConnections(List<String> input) {
        HashMap<String, Set<String>> connections = new HashMap<>();
        for (String line : input) {
            String[] split = line.split("-");
            if (connections.containsKey(split[0])) {
                connections.get(split[0]).add(split[1]);
            } else {
                connections.put(split[0], new HashSet<>(List.of(split[1])));
            }
            if (connections.containsKey(split[1])) {
                connections.get(split[1]).add(split[0]);
            } else {
                connections.put(split[1], new HashSet<>(List.of(split[0])));
            }
        }
        return connections;
    }

    public static int pathFinder(Stack<String> path, HashMap<String, Set<String>> connections, boolean isPart2) {
        String lastNode = path.peek();
        if (lastNode.equals("end")) {
            return 1;
        }
        int counter = 0;
        for (String nextNode : connections.get(lastNode)) {
            if (Character.isUpperCase(nextNode.charAt(0)) || !path.contains(nextNode)) {
                path.push(nextNode);
                counter += pathFinder(path, connections, isPart2);
                path.pop();
            } else if (isPart2 && !List.of("start", "end").contains(nextNode)) {
                path.push(nextNode);
                counter += pathFinder(path, connections, false);
                path.pop();
            }
        }
        return counter;
    }

}
