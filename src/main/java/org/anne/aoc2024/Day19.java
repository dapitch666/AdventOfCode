package org.anne.aoc2024;

import org.anne.common.Day;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day19 extends Day {
    public static void main(String[] args) {
        new Day19().run();
    }

    @Override
    public void execute() {
        setName("Linen Layout");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(List<String> input) {
        String[] patterns = input.get(0).split(", ");
        int count = 0;
        for (String design : input.subList(2, input.size())) {
            if (canMakeDesign(design, patterns, new HashMap<>())) {
                count++;
            }
        }
        return count;
    }

    public static long part2(List<String> input) {
        String[] patterns = input.get(0).split(", ");
        long count = 0L;
        for (String design : input.subList(2, input.size())) {
            if (canMakeDesign(design, patterns, new HashMap<>())) {
                count += countArrangements(design, patterns, new HashMap<>());
            }
        }
        return count;
    }

    static boolean canMakeDesign(String design, String[] patterns, Map<String, Boolean> memo) {
        if (design.isEmpty()) {
            return true;
        }
        if (memo.containsKey(design)) {
            return memo.get(design);
        }
        for (String pattern : patterns) {
            if (design.startsWith(pattern)) {
                if (canMakeDesign(design.substring(pattern.length()), patterns, memo)) {
                    memo.put(design, true);
                    return true;
                }
            }
        }
        memo.put(design, false);
        return false;
    }

    static long countArrangements(String design, String[] patterns, Map<String, Long> memo) {
        if (memo.containsKey(design)) {
            return memo.get(design);
        }
        long count = 0L;
        for (String pattern : patterns) {
            if (design.equals(pattern)) {
                count++;
            } else if (design.startsWith(pattern)) {
                count += countArrangements(design.substring(pattern.length()), patterns, memo);
            }
        }
        memo.put(design, count);
        return count;
    }
}
