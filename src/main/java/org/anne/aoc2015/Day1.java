package org.anne.aoc2015;

import org.anne.common.Day;


public class Day1 extends Day {
    public static void main(String[] args) {
        new Day1().run();
    }

    @Override
    public void execute() {
        setName("Not Quite Lisp");
        String input = readFileOneLine();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(String input) {
        return input.chars().map(c -> c == '(' ? 1 : -1).sum();
    }

    public static int part2(String input) {
        int floor = 0;
        for (int i = 0; i < input.length(); i++) {
            floor += (input.charAt(i) == '(') ? 1 : -1;
            if (floor == -1) {
                return i + 1;
            }
        }
        return -1;
    }
}
