package org.anne.aoc2018;

import org.anne.common.Day;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day12 extends Day {
    public static void main(String[] args) {
        new Day12().run();
    }

    @Override
    public void execute() {
        setName("Subterranean Sustainability");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(List<String> input) {
        Set<String> recipe = getRecipe(input.subList(2, input.size()));
        Set<Integer> pots = getPots(input.getFirst().substring(15));

        for (int i = 0; i < 20; i++) {
            pots = next(pots, recipe);
        }
        return pots.stream().mapToInt(Integer::intValue).sum();
    }

    public static long part2(List<String> input) {
        Set<String> recipe = getRecipe(input.subList(2, input.size()));
        Set<Integer> pots = getPots(input.getFirst().substring(15));

        int currentSum = pots.stream().mapToInt(Integer::intValue).sum();
        int diff = 0, i = 0;
        while (true) {
            i++;
            pots = next(pots, recipe);
            int nextSum = pots.stream().mapToInt(Integer::intValue).sum();
            if (nextSum - currentSum == diff) {
                break;
            }
            diff = nextSum - currentSum;
            currentSum = nextSum;
        }
        return pots.stream().mapToInt(Integer::intValue).sum() + (50000000000L - i) * diff;
    }

    private static Set<Integer> next(Set<Integer> pots, Set<String> recipe) {
        int start = pots.stream().min(Integer::compareTo).orElse(0);
        int end = pots.stream().max(Integer::compareTo).orElse(0);
        Set<Integer> next = new HashSet<>();
        for (int i = start - 3; i <= end + 3; i++) {
            StringBuilder pattern = new StringBuilder();
            for (int j = -2; j <= 2; j++) {
                pattern.append(pots.contains(i + j) ? '#' : '.');
            }
            if (recipe.contains(pattern.toString())) {
                next.add(i);
            }
        }
        return next;
    }

    private static Set<Integer> getPots(String init) {
        Set<Integer> pots = new HashSet<>();
        for (int i = 0; i < init.length(); i++) {
            if (init.charAt(i) == '#') {
                pots.add(i);
            }
        }
        return pots;
    }

    private static Set<String> getRecipe(List<String> input) {
        return input.stream()
                .filter(line -> line.endsWith("#"))
                .map(line -> line.substring(0, 5))
                .collect(Collectors.toSet());
    }
}
