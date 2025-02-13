package org.anne.aoc2015;

import org.anne.common.Day;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day9 extends Day {
    public static void main(String[] args) {
        new Day9().run();
    }

    @Override
    public void execute() {
        setName("All in a Single Night");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(List<String> input) {
        Map<String, Map<String, Integer>> distances = getDistances(input);
        int minDistance = Integer.MAX_VALUE;
        for (String start : distances.keySet()) {
            minDistance = Math.min(minDistance, bfs(distances, start, new HashMap<>(), 0, true));
        }
        return minDistance;
    }

    public static int part2(List<String> input) {
        Map<String, Map<String, Integer>> distances = getDistances(input);
        int maxDistance = 0;
        for (String start : distances.keySet()) {
            maxDistance = Math.max(maxDistance, bfs(distances, start, new HashMap<>(), 0, false));
        }
        return maxDistance;
    }

    private static int bfs(Map<String, Map<String, Integer>> distances, String start, Map<String, Integer> visited, int distance, boolean findMin) {
        visited.put(start, distance);
        if (visited.size() == distances.size()) {
            return distance;
        }
        int result = findMin ? Integer.MAX_VALUE : 0;
        for (String next : distances.get(start).keySet()) {
            if (!visited.containsKey(next)) {
                int nextDistance = bfs(distances, next, new HashMap<>(visited), distance + distances.get(start).get(next), findMin);
                result = findMin ? Math.min(result, nextDistance) : Math.max(result, nextDistance);
            }
        }
        return result;
    }

    private static Map<String, Map<String, Integer>> getDistances(List<String> input) {
        Map<String, Map<String, Integer>> distances = new HashMap<>();
        for (String line : input) {
            String[] parts = line.split(" ");
            String from = parts[0];
            String to = parts[2];
            int distance = Integer.parseInt(parts[4]);
            distances.computeIfAbsent(from, k -> new HashMap<>()).put(to, distance);
            distances.computeIfAbsent(to, k -> new HashMap<>()).put(from, distance);
        }
        return distances;
    }
}
