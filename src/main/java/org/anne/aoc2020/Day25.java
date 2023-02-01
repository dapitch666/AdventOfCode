package org.anne.aoc2020;

import org.anne.common.Day;

import java.util.List;

public class Day25 extends Day {

    private static final int DIVIDER = 20201227;

    public static void main(String[] args) {
        Day day = new Day25();
        List<Integer> input = day.readFileAsInts();
        day.setPart1(part1(input));
        day.setPart2(part2());
        day.printParts();
    }

    public static long part1(List<Integer> input) {
        int card = input.get(0);
        int door = input.get(1);
        int loopSize = 0;
        long result = 1;
        while (result != card) {
            loopSize++;
            result = result * 7 % DIVIDER;
        }
        result = 1;
        for (int i = 0; i < loopSize; i++) {
            result = result * door % DIVIDER;
        }
        return result;
    }

    public static String part2() {
        return "Merry Christmas!";
    }
}
