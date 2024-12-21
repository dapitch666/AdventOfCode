package org.anne.aoc2020;

import org.anne.common.Day;

import java.util.List;

public class Day25 extends Day {

    private static final int DIVIDER = 20201227;

    public static void main(String[] args) {
        Day day = new Day25();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Combo Breaker");
        List<Integer> input = this.readFileAsInts();
        this.setPart1(part1(input));
        this.setPart2(part2());
        this.printParts();
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

    @SuppressWarnings("SameReturnValue")
    public static String part2() {
        return "Merry Christmas!";
    }
}
