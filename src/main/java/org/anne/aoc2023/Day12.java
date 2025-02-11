package org.anne.aoc2023;

import org.anne.common.Day;

import java.util.*;

public class Day12 extends Day {
    public static void main(String[] args) {
        new Day12().run();
    }

    @Override
    public void execute() {
        setName("Hot Springs");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }


    public static long part1(List<String> input) {
        var sum = 0L;
        for (var line : input) {
            var parts = line.split(" ");
            var springs = parts[0];
            var groups = Arrays.stream(parts[1].split(","))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .toList();
            sum += getArrangements(springs, groups, new HashMap<>());
        }
        return sum;
    }

    public static long part2(List<String> input) {
        var sum = 0L;
        for (var line : input) {
            var parts = line.split(" ");
            var springs = parts[0] + "?" + parts[0] + "?" + parts[0] + "?" + parts[0] + "?" + parts[0];
            var tmp = Arrays.stream(parts[1].split(","))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .toList();
            var groups = new ArrayList<>(tmp);
            for (var i = 1; i < 5; i++) {
                groups.addAll(tmp);
            }
            sum += getArrangements(springs, groups, new HashMap<>());
        }
        return sum;
    }

    public static long getArrangements(String springs, List<Integer> groups, HashMap<Arrangement, Long> memo) {
        Arrangement arrangement = new Arrangement(springs, groups);
        if (memo.containsKey(arrangement)) {
            return memo.get(arrangement);
        }
        if (springs.isEmpty()) {
            return groups.isEmpty() ? 1 : 0;
        }
        long result = 0;
        if (springs.startsWith(".")) {
            result = getArrangements(springs.substring(1), groups, memo);
        } else if (springs.startsWith("?")) {
            result = getArrangements(springs.replaceFirst("\\?", "."), groups, memo) +
                    getArrangements(springs.replaceFirst("\\?", "#"), groups, memo);
        } else if (springs.startsWith("#")) {
            if (groups.isEmpty() || springs.length() < groups.get(0) || springs.split("\\.")[0].length() < groups.get(0)) {
                return 0;
            } else if (groups.size() == 1) {
                result = getArrangements(springs.substring(groups.get(0)), new ArrayList<>(), memo);
            } else if (springs.length() < groups.get(0) + 1 || springs.charAt(groups.get(0)) == '#') {
                return 0;
            } else {
                result = getArrangements(springs.substring(groups.get(0) + 1), groups.subList(1, groups.size()), memo);
            }
        }
        memo.put(arrangement, result);
        return result;
    }
    
    public record Arrangement(String springs, List<Integer> groups) {}
}
