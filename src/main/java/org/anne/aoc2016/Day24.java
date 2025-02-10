package org.anne.aoc2016;

import org.anne.common.Day;
import org.anne.common.GridHelper;

import java.awt.*;
import java.util.*;
import java.util.List;


public class Day24 extends Day {
    public static void main(String[] args) {
        new Day24().run();
    }

    @Override
    public void execute() {
        setName("Air Duct Spelunking");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(List<String> input) {
        char[][] grid = GridHelper.getCharGrid(input);
        Map<Integer, Point> pois = GridHelper.findAllChar(grid, Character::isDigit)
                .entrySet().stream()
                .collect(HashMap::new, (m, e) -> m.put(Character.getNumericValue(e.getKey()), e.getValue()), HashMap::putAll);
        return bfs(grid, pois, false);
    }

    public static int part2(List<String> input) {
        char[][] grid = GridHelper.getCharGrid(input);
        Map<Integer, Point> pois = GridHelper.findAllChar(grid, Character::isDigit)
                .entrySet().stream()
                .collect(HashMap::new, (m, e) -> m.put(Character.getNumericValue(e.getKey()), e.getValue()), HashMap::putAll);
        return bfs(grid, pois, true);
    }

    private static int bfs(char[][] grid, Map<Integer, Point> pois, boolean returnToStart) {
        int size = pois.size();
        int[][] distances = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                int distance = GridHelper.findShortestPath(grid, pois.get(i), pois.get(j), c -> c != '#').size() - 1;
                distances[i][j] = distances[j][i] = distance;
            }
        }
        int min = Integer.MAX_VALUE;
        List<List<Integer>> orders = new ArrayList<>();
        generatePermutations(new ArrayList<>(pois.keySet()), 0, orders);
        for (List<Integer> order : orders) {
            int distance = distances[0][order.getFirst()];
            for (int i = 0; i < order.size() - 1; i++) {
                distance += distances[order.get(i)][order.get(i + 1)];
            }
            if (returnToStart) {
                distance += distances[order.getLast()][0];
            }
            min = Math.min(min, distance);
        }
        return min;
    }

    private static void generatePermutations(List<Integer> nums, int start, List<List<Integer>> result) {
        if (start == nums.size()) {
            result.add(new ArrayList<>(nums));
            return;
        }
        for (int i = start; i < nums.size(); i++) {
            Collections.swap(nums, start, i);
            generatePermutations(nums, start + 1, result);
            Collections.swap(nums, start, i);
        }
    }
}
