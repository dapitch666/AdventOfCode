package org.anne.aoc2023;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 extends Day {
    public static void main(String[] args) {
        Day day = new Day1();
        List<String> input = day.readFile();
        day.setPart1(part1(input)); //Part 1: 54561
        day.setPart2(part2(input)); //Part 2: 54076
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
            for (String word : words) {
                if (line.contains(word)) {
                    int fIdx = line.indexOf(word);
                    int lIdx = line.lastIndexOf(word);
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
        
        int digit = line.chars()
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
