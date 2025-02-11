package org.anne.aoc2017;

import org.anne.common.Day;

import java.util.*;
import java.util.stream.IntStream;

public class Day6 extends Day {
    public static void main(String[] args) {
        new Day6().run();
    }

    @Override
    public void execute() {
        setName("Memory Reallocation");
        List<Integer> input = readFileGetAllInts();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(List<Integer> input) {
        int[] banks = input.stream().mapToInt(i -> i).toArray();
        return getCycleResults(banks).size();
    }

    public static int part2(List<Integer> input) {
        int[] banks = input.stream().mapToInt(i -> i).toArray();
        var cycleResults = getCycleResults(banks);
        return cycleResults.size() - new ArrayList<>(cycleResults).indexOf(toString(banks));
    }

    private static Set<String> getCycleResults(int[] banks) {
        Set<String> cycleResults = new LinkedHashSet<>();
        while (cycleResults.add(toString(banks))) {
            int index = IntStream.range(0, banks.length)
                    .reduce((i, j) -> banks[j] <= banks[i] ? i : j)
                    .orElseThrow();
            int blocks = banks[index];
            banks[index] = 0;
            IntStream.range(0, blocks).forEach(i -> banks[(index + i + 1) % banks.length]++);
        }
        return cycleResults;
    }

    private static String toString(int[] banks) {
        return Arrays.stream(banks).mapToObj(String::valueOf).reduce((a, b) -> a + "," + b).orElseThrow();
    }
}
