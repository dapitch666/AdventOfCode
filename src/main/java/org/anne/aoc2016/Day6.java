package org.anne.aoc2016;

import org.anne.common.Day;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day6 extends Day {
    public static void main(String[] args) {
        new Day6().run();
    }

    @Override
    public void execute() {
        setName("Signals and Noise");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static String part1(List<String> input) {
        return decodeMessage(input, true);
    }

    public static String part2(List<String> input) {
        return decodeMessage(input, false);
    }

    private static String decodeMessage(List<String> input, boolean mostCommon) {
        StringBuilder result = new StringBuilder(input.getFirst().length());
        for (int i = 0; i < input.getFirst().length(); i++) {
            int index = i;
            result.append(input.stream()
                    .map(line -> line.charAt(index))
                    .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                    .entrySet().stream()
                    .max((e1, e2) -> mostCommon ? e1.getValue().compareTo(e2.getValue()) : e2.getValue().compareTo(e1.getValue()))
                    .map(Map.Entry::getKey)
                    .orElseThrow());
        }
        return result.toString();
    }
}