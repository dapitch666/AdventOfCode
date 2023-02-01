package org.anne.aoc2021;

import org.anne.common.Day;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Day10 extends Day {
    private static final Map<Character, Character> map = Map.of(
            '(', ')',
            '[', ']',
            '{', '}',
            '<', '>'
    );

    public static void main(String[] args) {
        Day day = new Day10();
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    public static long part1(List<String> input) {
        return input.stream().mapToLong(Day10::getCorruptedScore).sum();
    }

    public static long part2(List<String> input) {
        List<Long> scores = input.stream()
                .map(Day10::getIncompleteScore)
                .filter(l -> l > 0L)
                .sorted()
                .toList();
        return scores.get(scores.size() / 2);
    }

    public static long getCorruptedScore(String line) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Integer> score = Map.of(
                ')', 3,
                ']', 57,
                '}', 1197,
                '>', 25137
        );
        for (char c : line.toCharArray()) {
            if (map.containsKey(c)) {
                stack.push(c);
            } else if (map.get(stack.peek()) == c) {
                stack.pop();
            } else {
                return score.get(c);
            }
        }
        return 0L;
    }

    public static long getIncompleteScore(String line) {
        Stack<Character> stack = new Stack<>();
        for (char c : line.toCharArray()) {
            if (map.containsKey(c)) {
                stack.push(map.get(c));
            } else if (stack.peek() == c) {
                stack.pop();
            } else {
                return 0L;
            }
        }
        return calculateScore(stack);
    }

    public static long calculateScore(Stack<Character> stack) {
        final Map<Character, Integer> incompleteScore = Map.of(
                ')', 1,
                ']', 2,
                '}', 3,
                '>', 4
        );
        long score = 0L;
        while (!stack.empty()) {
            score = score * 5 + incompleteScore.get(stack.pop());
        }
        return score;
    }
}
