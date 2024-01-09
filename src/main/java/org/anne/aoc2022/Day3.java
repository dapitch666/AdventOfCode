package org.anne.aoc2022;

import org.anne.common.Day;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day3 extends Day {
    public static void main(String[] args) {
        Day day = new Day3();
        day.setName("Rucksack Reorganization");
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    public static int part1(List<String> input) {
        int sum = 0;
        for (String line : input) {
            Set<Integer> backpack = getItems(line.substring(0, (line.length() / 2)));
            backpack.retainAll(getItems(line.substring(line.length() / 2)));
            sum += backpack.stream().findFirst().orElseThrow();
        }
        return sum;
    }

    public static int part2(List<String> input) {
        int sum = 0;
        for (int i = 0; i < input.size(); i += 3) {
            Set<Integer> backpack = getItems(input.get(i));
            backpack.retainAll(getItems(input.get(i+1)));
            backpack.retainAll(getItems(input.get(i+2)));
            sum += backpack.stream().findFirst().orElseThrow();
        }
        return sum;
    }

    public static int valueOfChar(char c) {
        if ((int)c < 91) return (int)c - 38;
        return (int)c - 96;
    }

    public static Set<Integer> getItems (String s) {
        return s.chars()
                .mapToObj(e -> valueOfChar((char)e))
                .collect(Collectors.toSet());
    }
}
