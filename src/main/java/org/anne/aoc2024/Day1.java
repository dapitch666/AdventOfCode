package org.anne.aoc2024;

import org.anne.common.Day;

import java.util.Collections;
import java.util.List;

public class Day1 extends Day {
    public static void main(String[] args) {
        new Day1().run();
    }

    @Override
    public void execute() {
        setName("Historian Hysteria");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(List<String> input) {
        var list1 = input.stream()
                .map(s -> Integer.parseInt(s.split("\\s+")[0]))
                .sorted()
                .toList();
        var list2 = input.stream()
                .map(s -> Integer.parseInt(s.split("\\s+")[1]))
                .sorted()
                .toList();
        int sum = 0;
        for (int i = 0; i < input.size(); i++) {
            sum += Math.abs(list1.get(i) - list2.get(i));
        }
        return sum;
    }

    public static int part2(List<String> input) {
        var list2 = input.stream()
                .map(s -> Integer.parseInt(s.split("\\s+")[1]))
                .toList();
        var list1 = input.stream()
                .map(s -> Integer.parseInt(s.split("\\s+")[0]))
                .filter(list2::contains)
                .toList();
        return list1.stream()
                .mapToInt(i -> i * Collections.frequency(list2, i))
                .sum();
    }
}
