package org.anne.aoc2024;

import org.anne.common.Day;

import java.util.Arrays;
import java.util.List;

public class Day7 extends Day {
    public static void main(String[] args) {
        new Day7().run();
    }

    @Override
    public void execute() {
        setName("Bridge Repair");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static long part1(List<String> input) {
        long sumValid = 0;
        for (String line : input) {
            var parts = line.split(": ");
            long result = Long.parseLong(parts[0]);
            if (isValid(result, parts[1], false)) {
                sumValid += result;
            }
        }
        return sumValid;
    }

    public static long part2(List<String> input) {
        long sumValid = 0;
        for (String line : input) {
            var parts = line.split(": ");
            long result = Long.parseLong(parts[0]);
            if (isValid(result, parts[1], true)) {
                sumValid += result;
            }
        }
        return sumValid;
    }

    private static boolean isValid(long result, String part, boolean addConcatenate) {
        var numbers = Arrays.stream(part.split(" ")).map(Long::parseLong).toArray(Long[]::new);
        return evaluate(numbers, 0, numbers[0], result, addConcatenate);
    }

    private static boolean evaluate(Long[] numbers, int index, long currentResult, long target, boolean addConcatenate) {
        if (currentResult > target) {
            return false;
        }
        if (index == numbers.length - 1) {
            return currentResult == target;
        }
        if (addConcatenate) {
            return  evaluate(numbers, index + 1, currentResult + numbers[index + 1], target, true) ||
                    evaluate(numbers, index + 1, currentResult * numbers[index + 1], target, true) ||
                    evaluate(numbers, index + 1, Long.parseLong(currentResult + "" + numbers[index + 1]), target, true);
        } else {
            return  evaluate(numbers, index + 1, currentResult + numbers[index + 1], target, false) ||
                    evaluate(numbers, index + 1, currentResult * numbers[index + 1], target, false);
        }
    }
}
