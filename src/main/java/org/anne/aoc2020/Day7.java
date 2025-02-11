package org.anne.aoc2020;

import org.anne.common.Day;

import java.util.*;
import java.util.stream.Collectors;

public class Day7 extends Day {
    private static final String MY_BAG = "shiny gold";

    public static void main(String[] args) {
        new Day7().run();
    }

    @Override
    public void execute() {
        setName("Handy Haversacks");
        Map<String, Map<String, Integer>> input = decode(readFile());
        setPart1(part1(input));
        setPart2(part2(input));
    }

    static int part1(Map<String, Map<String, Integer>> input) {
        Set<String> okBags = new TreeSet<>();
        Set<String> okBagsTmp = new TreeSet<>();
        boolean newBagsAdded = okBags.addAll(getBags(input, MY_BAG));
        while (newBagsAdded) {
            for (String b : okBags) {
                okBagsTmp.addAll(getBags(input, b));
            }
            newBagsAdded = okBags.addAll(okBagsTmp);
            okBagsTmp.clear();
        }
        return okBags.size();
    }

    static int part2(Map<String, Map<String, Integer>> input) {
        return getNbBags(MY_BAG, input);
    }

    private static int getNbBags(String color, Map<String, Map<String, Integer>> bags) {
        int count = 0;
        if(!bags.containsKey(color)) {
            return 0;
        }
        for (Map.Entry<String, Integer> entry : bags.get(color).entrySet()) {
            String bagColor = entry.getKey();
            int nbBags = entry.getValue();
            count += nbBags + nbBags * getNbBags(bagColor, bags);
        }
        return count;
    }

    private static Set<String> getBags(Map<String, Map<String, Integer>> bags, String bagColor) {
        Set<String> bagsSet = new TreeSet<>();
        bags.forEach((key, value) -> {
            if (value.containsKey(bagColor)) {
                bagsSet.add(key);
            }
        });
        return bagsSet;
    }

    static Map<String, Map<String, Integer>> decode(List<String> input) {
        Map<String, Map<String, Integer>> bags = new HashMap<>();
        for (String line : input) {
            String[] lineSplit = line.split(" bags contain ");
            String[] lineSplitSplit = lineSplit[1].split(", ");
            Map<String, Integer> bagList = Arrays.stream(lineSplitSplit)
                    .filter(s -> !s.equals("no other bags."))
                    .map(Day7::color)
                    .collect(Collectors.toMap(e -> e[1], e -> Integer.parseInt(e[0])));
            bags.put(lineSplit[0], bagList);
        }
        return bags;
    }

    private static String[] color(String s) {
        return new String[]{s.substring(0, 1), s.replaceFirst("^\\d (\\w+ \\w+) .*$", "$1")};
    }
}
