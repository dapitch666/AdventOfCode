package org.anne.aoc2020;

import org.anne.common.Day;

import java.util.*;

public class Day15 extends Day {

    public static void main(String[] args) {
        new Day15().run();
    }

    @Override
    public void execute() {
        setName("Rambunctious Recitation");
        List<Integer> input = readFileAsInts();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(List<Integer> input) {
        return getLastSpokenNumber(input, 2020);
    }

    public static int part2(List<Integer> input) {
        return getLastSpokenNumber(input, 30000000);
    }

    static int getLastSpokenNumber(List<Integer> input, int maxTurns) {
        int spokenNumber = 0;
        Map<Integer, Integer> spokenNumbers = new HashMap<>();
        for (int i = 0; i < input.size(); i++) {
            spokenNumbers.put(input.get(i), i + 1);
        }
        for (int i = input.size() + 1; i < maxTurns; i++) {
            if (spokenNumbers.containsKey(spokenNumber)) {
                int val = spokenNumbers.get(spokenNumber);
                spokenNumbers.put(spokenNumber, i);
                spokenNumber = i - val;
            } else {
                spokenNumbers.put(spokenNumber, i);
                spokenNumber = 0;
            }
        }
        return spokenNumber;
    }
}
