package org.anne.aoc2021;

import org.anne.common.Day;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day14 extends Day {

    public static void main(String[] args) {
        Day day = new Day14();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Extended Polymerization");
        List<String> input = this.readFile();
        this.setPart1(part1(input));
        this.setPart2(part2(input));
        this.printParts();
    }

    public static long part1(List<String> input) {
        return polymerize(input, 10);
    }

    public static long part2(List<String> input) {
        return polymerize(input, 40);
    }

    static long polymerize(List<String> input, int steps) {
        String polymerTemplate = input.get(0);
        Map<String, Long> pairs = new HashMap<>();

        Map<String, String> insertionRule = input.stream()
                .skip(2)
                .map(s -> s.split(" -> "))
                .collect(Collectors.toMap(e -> e[0], e -> e[1]));

        for (int i = 0; i < polymerTemplate.length() - 1; i++) {
            pairs.merge(polymerTemplate.substring(i, i + 2), 1L, Long::sum);
        }

        Map<Character, Long> charMap = polymerTemplate.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), HashMap::new, Collectors.counting()));

        for (int i = 0; i < steps; i++) {
            Map<String, Long> newPairs = new HashMap<>();
            pairs.forEach((key, value) -> {
                String insertedChar = insertionRule.get(key);
                newPairs.merge(key.charAt(0) + insertedChar, value, Long::sum);
                newPairs.merge(insertedChar + key.charAt(1), value, Long::sum);
                charMap.merge(insertedChar.charAt(0), value, Long::sum);
            });
            pairs = newPairs;
        }
        return calculateResult(charMap);
    }

    private static Long calculateResult(Map<Character, Long> charMap) {
        Long max = charMap.values().stream().mapToLong(Long::longValue).max().orElse(0);
        Long min = charMap.values().stream().mapToLong(Long::longValue).min().orElse(0);
        return max - min;
    }
}
