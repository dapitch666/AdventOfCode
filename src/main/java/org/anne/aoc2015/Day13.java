package org.anne.aoc2015;

import org.anne.common.Day;

import java.util.*;

public class Day13 extends Day {
    public static void main(String[] args) {
        new Day13().run();
    }

    @Override
    public void execute() {
        setName("Knights of the Dinner Table");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(List<String> input) {
        Map<String, Map<String, Integer>> map = getMap(input);
        return getBestHappiness(map);
    }

    public static int part2(List<String> input) {
        Map<String, Map<String, Integer>> map = getMap(input);
        map.put("me", new HashMap<>());
        for (String person : map.keySet()) {
            map.get(person).put("me", 0);
            map.get("me").put(person, 0);
        }
        return getBestHappiness(map);
    }

    private static int getBestHappiness(Map<String, Map<String, Integer>> map) {
        List<List<String>> orders = new ArrayList<>();
        generatePermutations(new ArrayList<>(map.keySet()), 0, orders);
        return orders.stream().mapToInt(order -> totalHappiness(map, order)).max().orElse(0);
    }

    private static int totalHappiness(Map<String, Map<String, Integer>> happiness, List<String> order) {
        int total = 0;
        for (int i = 0; i < order.size(); i++) {
            total += happiness.get(order.get(i)).get(order.get((i + order.size() - 1) % order.size()))
                    + happiness.get(order.get(i)).get(order.get((i + 1) % order.size()));
        }
        return total;
    }

    private static Map<String, Map<String, Integer>> getMap(List<String> input) {
        return input.stream()
                .map(line -> line.split("[ .]"))
                .collect(HashMap::new, (m, parts) -> m.computeIfAbsent(parts[0], k -> new HashMap<>())
                        .put(parts[10], Integer.parseInt(parts[3]) * (parts[2].equals("lose") ? -1 : 1)), HashMap::putAll);
    }

    private static void generatePermutations(List<String> order, int start, List<List<String>> result) {
        if (start == order.size()) {
            result.add(new ArrayList<>(order));
            return;
        }
        for (int i = start; i < order.size(); i++) {
            Collections.swap(order, start, i);
            generatePermutations(order, start + 1, result);
            Collections.swap(order, start, i);
        }
    }
}
