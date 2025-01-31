package org.anne.aoc2017;

import org.anne.common.Day;

public class Day9 extends Day {
    public static void main(String[] args) {
        new Day9().run();
    }

    @Override
    public void execute() {
        setName("Stream Processing");
        String input = readFileOneLine();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(String input) {
        return getResult(input, true);
    }

    public static int part2(String input) {
        return getResult(input, false);
    }

    private static int getResult(String input, boolean getScore) {
        boolean garbage = false;
        int score = 0, depth = 1, garbageCount = 0;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '!') {
                i++;
            } else if (garbage) {
                if (c == '>') {
                    garbage = false;
                } else {
                    garbageCount++;
                }
            } else {
                if (c == '<') {
                    garbage = true;
                } else if (c == '{') {
                    score += depth++;
                } else if (c == '}') {
                    depth--;
                }
            }
        }
        return getScore ? score : garbageCount;
    }
}
