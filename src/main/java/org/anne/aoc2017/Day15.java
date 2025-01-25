package org.anne.aoc2017;

import org.anne.common.Day;
import java.util.List;

public class Day15 extends Day {
    public static void main(String[] args) {
        new Day15().run();
    }

    @Override
    public void execute() {
        setName("Dueling Generators");
        List<Integer> input = readFileGetAllInts();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(List<Integer> input) {
        return countEquals(40000000, input.get(0), 1, input.get(1), 1);
    }

    public static int part2(List<Integer> input) {
        return countEquals(5000000, input.get(0), 4, input.get(1), 8);
    }

    private static int countEquals(int x, long a, int multipleA, long b, int multipleB) {
        int count = 0;
        for (int i = 0; i < x; i++) {
            a = next(a, 16807, multipleA);
            b = next(b, 48271, multipleB);
            if ((a & 0xFFFF) == (b & 0xFFFF)) {
                count++;
            }
        }
        return count;
    }

    static long next(long l, int factor, int multiple) {
        do {
            l = l * factor % 2147483647;
        } while (multiple != 1 && l % multiple != 0);
        return l;
    }
}