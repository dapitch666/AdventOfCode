package org.anne.aoc2016;

import org.anne.common.Day;

public class Day19 extends Day {
    public static void main(String[] args) {
        new Day19().run();
    }

    @Override
    public void execute() {
        setName("An Elephant Named Joseph");
        int input = Integer.parseInt(readFileOneLine());
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(int input) {
        int elf = 1;
        for (int i = 1; i <= input; i++) {
            elf = (elf + 2 > i) ? 1 : elf + 2;
        }
        return elf;
    }

    public static int part2(int input) {
        int elf = 1, prev = 1, increment = 1;
        for (int i = 1; i <= input + 1; i++) {
            increment = (elf >= prev) ? 2 : increment;
            elf += increment;
            if (elf >= i) {
                prev = i - increment;
                elf = 1;
                increment = 1;
            }
        }
        return elf;
    }
}
