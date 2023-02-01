package org.anne.aoc2020;


import org.anne.common.Day;

import java.util.*;
import java.util.stream.Stream;

public class Day6 extends Day {

    public static void main(String[] args) {
        Day day = new Day6();
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    static long part1(List<String> input) {
        return count(input, true);
    }

    static long part2(List<String> input) {
        return count(input, false);
    }

    private static long count(List<String> input, boolean isPart1) {
        input.add("");
        List<String> tempStr = new ArrayList<>();
        long sum = 0;
        for (String line : input) {
            if (line.trim().isEmpty()) {
                sum += isPart1 ? countDistinctChar(tempStr) : countAnswers(tempStr);
                tempStr.clear();
            } else {
                tempStr.add(line);
            }
        }
        return sum;
    }

    private static long countDistinctChar(List<String> input) {
        Stream<Character> chars = String.join("", input).chars().mapToObj(c -> (char)c);
        return chars.distinct().count();
    }

    private static long countAnswers(List<String> input) {
        Map<Character, Integer> charCount = characterCount(String.join("", input));
        return Collections.frequency(charCount.values(), input.size());
    }

    private static Map<Character, Integer> characterCount(String str) {
        Map<Character, Integer> frequencies = new HashMap<>();
        for (char c : str.toCharArray()) {
            frequencies.merge(c,1, Integer::sum);
        }
        return frequencies;
    }


}
