package org.anne.aoc2020;

import org.anne.common.Day;

import java.util.*;
import java.util.stream.Collectors;

public class Day21 extends Day {

    public static void main(String[] args) {
        new Day21().run();
    }

    @Override
    public void execute() {
        setName("Allergen Assessment");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static long part1(List<String> input) {
        Map<String, Integer> ingredientsOccurrences = new HashMap<>();
        Map<String, Set<String>> allergensIngredients = new TreeMap<>();
        for (String line : input) {
            String[] split = line.split("\\(");
            List<String> ingredients = Arrays.stream(split[0].split(" ")).toList();
            List<String> allergens = Arrays.stream(split[1]
                    .replaceFirst("contains ", "")
                    .replaceFirst("\\)", "")
                    .split(", ")).toList();
            for (String ingredient : ingredients) {
                ingredientsOccurrences.put(ingredient, ingredientsOccurrences.getOrDefault(ingredient, 0) + 1);
            }
            for (String allergen : allergens) {
                Set<String> value;
                if (allergensIngredients.containsKey(allergen)) {
                    value = allergensIngredients.get(allergen).stream().filter(ingredients::contains).collect(Collectors.toSet());
                } else {
                    value = new HashSet<>(ingredients);
                }
                allergensIngredients.put(allergen, value);
            }
        }

        Set<String> badIngredients = new HashSet<>();
        for (Map.Entry<String, Set<String>> allergensIngredient : allergensIngredients.entrySet()) {
            badIngredients.addAll(allergensIngredient.getValue());
        }
        int count = 0;
        for (Map.Entry<String, Integer> occ : ingredientsOccurrences.entrySet()) {
            if (!badIngredients.contains(occ.getKey())) {
                count += occ.getValue();
            }
        }

        return count;
    }

    public static String part2(List<String> input) {
        Map<String, Integer> ingredientsOccurrences = new HashMap<>();
        Map<String, Set<String>> allergensIngredients = new TreeMap<>();
        for (String line : input) {
            String[] split = line.split("\\(");
            List<String> ingredients = Arrays.stream(split[0].split(" ")).toList();
            List<String> allergens = Arrays.stream(split[1]
                    .replaceFirst("contains ", "")
                    .replaceFirst("\\)", "")
                    .split(", ")).toList();
            for (String ingredient : ingredients) {
                ingredientsOccurrences.put(ingredient, ingredientsOccurrences.getOrDefault(ingredient, 0) + 1);
            }
            for (String allergen : allergens) {
                Set<String> value;
                if (allergensIngredients.containsKey(allergen)) {
                    value = allergensIngredients.get(allergen).stream().filter(ingredients::contains).collect(Collectors.toSet());
                } else {
                    value = new HashSet<>(ingredients);
                }
                allergensIngredients.put(allergen, value);
            }
        }
        while (isDuplicated(allergensIngredients)) {
            for (Set<String> ingredients : allergensIngredients.values()) {
                if (ingredients.size() == 1) {
                    for (String allergen : allergensIngredients.keySet()) {
                        if (allergensIngredients.get(allergen) != ingredients) {
                            allergensIngredients.get(allergen).remove(ingredients.iterator().next());
                        }
                    }
                }
            }
        }
        return allergensIngredients.values().stream().map(String::valueOf)
                .collect(Collectors.joining(","))
                .replaceAll("]", "")
                .replaceAll("\\[", "");

    }

    private static boolean isDuplicated(Map<String, Set<String>> map) {
        for (Map.Entry<String, Set<String>> mapEntry : map.entrySet()) {
            if (mapEntry.getValue().size() > 1) {
                return true;
            }
        }
        return false;
    }

}
