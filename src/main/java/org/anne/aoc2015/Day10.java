package org.anne.aoc2015;

import org.anne.common.Day;

public class Day10 extends Day {
    public static void main(String[] args) {
        new Day10().run();
    }

    @Override
    public void execute() {
        setName("Elves Look, Elves Say");
        String input = readFileOneLine();
        setPart1(part1(input, 40));
        setPart2(part2(input));
    }

    public static int part1(String input, int times) {
        return gameOfLife(input, times);
    }

    public static int part2(String input) {
        return gameOfLife(input, 50);
    }

    private static int gameOfLife(String input, int iterations) {
        StringBuilder current = new StringBuilder(input);
        for (int i = 0; i < iterations; i++) {
            StringBuilder next = new StringBuilder();
            int count = 1;
            for (int j = 1; j < current.length(); j++) {
                if (current.charAt(j) == current.charAt(j - 1)) {
                    count++;
                } else {
                    next.append(count).append(current.charAt(j - 1));
                    count = 1;
                }
            }
            next.append(count).append(current.charAt(current.length() - 1));
            current = next;
        }
        return current.length();
    }
}
