package org.anne.aoc2017;

import org.anne.common.Day;

import java.util.List;

public class Day1 extends Day {
    public static void main(String[] args) {
        new Day1().run();
    }

    @Override
    public void execute() {
        setName("Inverse Captcha");
        String input = readFileOneLine();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(String input) {
        int sum = 0;
        char prev = input.charAt(input.length() - 1);
        for (char c : input.toCharArray()) {
            if (c == prev) {
                sum += c - '0';
            } else {
                prev = c;
            }
        }
        return sum;
    }

    public static int part2(String input) {
        int sum = 0;
        int length = input.length();
        int step = length / 2;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == input.charAt((i + step) % length)) {
                sum += input.charAt(i) - '0';
            }
        }
        return sum;
    }
}
