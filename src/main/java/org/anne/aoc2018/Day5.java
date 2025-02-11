package org.anne.aoc2018;

import org.anne.common.Day;

import java.util.Stack;
import java.util.stream.Collectors;

public class Day5 extends Day {
    public static void main(String[] args) {
        new Day5().run();
    }

    @Override
    public void execute() {
        setName("Alchemical Reduction");
        String input = readFileOneLine();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(String input) {
        return reduce(input).length();
    }

    public static int part2(String input) {
        input = reduce(input);
        int min = Integer.MAX_VALUE;
        for (char c = 'a'; c <= 'z'; c++) {
            int length = reduce(input.replaceAll("[" + c + (char) (c - 32) + "]", "")).length();
            if (length < min) {
                min = length;
            }
        }
        return min;
    }

    private static String reduce(String input) {
        Stack<Character> stack = new Stack<>();
        for (char c : input.toCharArray()) {
            if (stack.isEmpty() || Math.abs(stack.peek() - c) != 32) {
                stack.push(c);
            } else {
                stack.pop();
            }
        }
        return stack.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}
