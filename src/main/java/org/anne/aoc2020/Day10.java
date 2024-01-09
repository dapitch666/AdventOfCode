package org.anne.aoc2020;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day10 extends Day {

    public static void main(String[] args) {
        Day day = new Day10();
        day.setName("Adapter Array");
        List<Integer> input = day.readFileAsInts();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    static int part1(List<Integer> input) {
        List<Integer> adapters = new ArrayList<>(input);
        Collections.sort(adapters);
        int one = 0, three = 1, previousAdapter = 0;
        for (int adapter : adapters) {
            int joltDiff = adapter - previousAdapter;
            if (joltDiff == 1) {
                one++;
            } else if (joltDiff == 3) {
                three++;
            }
            previousAdapter = adapter;
        }
        return one * three;
    }

    static long part2(List<Integer> input) {
        List<Integer> adapters = new ArrayList<>(input);
        adapters.add(0);
        Collections.sort(adapters);
        adapters.add(adapters.get(adapters.size() - 1) + 3);
        List<Integer> tmpList = new ArrayList<>();
        tmpList.add(0);
        long count = 1;
        for (int i = 0; i < adapters.size() - 1; i++) {
            int currentAdapter = adapters.get(i);
            int nextAdapter = adapters.get(i + 1);
            if (nextAdapter - currentAdapter != 1) {
                int multiplier = 1;
                switch (tmpList.size()) {
                    case 3 -> multiplier = 2;
                    case 4 -> multiplier = 4;
                    case 5 -> multiplier = 7;
                }
                count = count * multiplier;
                tmpList.clear();
            }
            tmpList.add(nextAdapter);
        }
        return count;
    }
}
