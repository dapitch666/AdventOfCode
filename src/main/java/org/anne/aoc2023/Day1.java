package org.anne.aoc2023;

import org.anne.common.Day;

import java.util.Arrays;
import java.util.List;

public class Day1 extends Day {
    public static void main(String[] args) {
        Day day = new Day1();
        day.setName("Trebuchet?!");
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    public static int part1(List<String> input) {
        return input.stream()
                .map(line -> calibrationValue(line, false))
                .reduce(Integer::sum).orElse(0);
    }

    public static int part2(List<String> input) {
        return input.stream()
                .map(line -> calibrationValue(line, true))
                .reduce(Integer::sum).orElse(0);
    }
    
    private static int calibrationValue(String line, boolean isPart2) {
        String[] words = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        int tens = 0, unit = 0, firstIdx = Integer.MAX_VALUE, lastIdx = 0;
        
        if(isPart2) {
            for (var word : words) {
                if (line.contains(word)) {
                    var fIdx = line.indexOf(word);
                    var lIdx = line.lastIndexOf(word);
                    if (fIdx < firstIdx) {
                        firstIdx = fIdx;
                        tens = Arrays.asList(words).indexOf(word) + 1;
                    }
                    if (lIdx > lastIdx) {
                        lastIdx = lIdx;
                        unit = Arrays.asList(words).indexOf(word) + 1;
                    }
                }
            }
        }
        
        var digit = line.chars()
                .filter(Character::isDigit)
                .map(Character::getNumericValue)
                .findFirst().orElse(0);
        if (digit > 0 && line.indexOf(String.valueOf(digit)) < firstIdx) {
            tens = digit;
        }
        
        digit = line.chars()
                .filter(Character::isDigit)
                .map(Character::getNumericValue)
                .reduce((first, second) -> second).orElse(0);
        if (digit > 0 && line.lastIndexOf(String.valueOf(digit)) >= lastIdx) {
            unit = digit;
        }
        return tens * 10 + unit;
    }
}
