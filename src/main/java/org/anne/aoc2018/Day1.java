package org.anne.aoc2018;

import org.anne.common.Day;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day1 extends Day {
    public static void main(String[] args) {
        new Day1().run();
    }

    @Override
    public void execute() {
        setName("Chronal Calibration");
        List<Integer> input = readFileAsInts();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(List<Integer> input) {
        return input.stream().mapToInt(Integer::intValue).sum();
    }

    public static int part2(List<Integer> input) {
        int current = 0;
        Set<Integer> seen = new HashSet<>();
        seen.add(0);
        while (true) {
            for (int i : input) {
                current += i;
                if (!seen.add(current)) {
                    return current;
                }
            }
        }
    }
}