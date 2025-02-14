package org.anne.aoc2015;

import org.anne.common.Day;
import org.anne.common.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day15 extends Day {
    public static void main(String[] args) {
        new Day15().run();
    }

    @Override
    public void execute() {
        setName("Science for Hungry People");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(List<String> input) {
        return getBestScore(input, 0);
    }

    public static int part2(List<String> input) {
        return getBestScore(input, 500);
    }

    private static int getBestScore(List<String> input, int calories) {
        List<int[]> ingredients = input.stream()
                .map(Utils::inputToIntStream)
                .map(IntStream::toArray)
                .toList();
        List<List<Integer>> recipes = mix(100, ingredients.size());
        return recipes.stream()
                .mapToInt(r -> score(r, ingredients, calories))
                .max()
                .orElseThrow();
    }

    private static List<List<Integer>> mix(int total, int n) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = (n == 1) ? total : 0; i <= total; i++) {
            int left = total - i;
            if (n > 1) {
                for (List<Integer> subMix : mix(left, n - 1)) {
                    List<Integer> mix = new ArrayList<>(subMix);
                    mix.addFirst(i);
                    result.add(mix);
                }
            } else {
                result.add(List.of(i));
            }
        }
        return result;
    }

    private static int score(List<Integer> recipe, List<int[]> ingredients, int maxCalories) {
        int[] cookie = new int[5];
        for (int i = 0; i < ingredients.size(); i++) {
            for (int j = 0; j < 5; j++) {
                cookie[j] += ingredients.get(i)[j] * recipe.get(i);
            }
        }
        if (maxCalories > 0 && cookie[4] > maxCalories) {
            return 0;
        }
        return Arrays.stream(cookie, 0, 4)
                .map(i -> Math.max(i, 0))
                .reduce(1, (a, b) -> a * b);
    }
}
