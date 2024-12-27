package org.anne.aoc2018;

import org.anne.common.Day;

import java.util.*;

public class Day4 extends Day {
    public static void main(String[] args) {
        new Day4().run();
    }

    @Override
    public void execute() {
        setName("Repose Record");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(List<String> input) {
        Map<Integer, int[]> guards = getGuards(input);
        return calculateMaxGuardMinute(guards, true);
    }

    public static int part2(List<String> input) {
        Map<Integer, int[]> guards = getGuards(input);
        return calculateMaxGuardMinute(guards, false);
    }

    private static int calculateMaxGuardMinute(Map<Integer, int[]> guards, boolean byTotalMinutes) {
        int maxGuard = 0, maxMinute = 0, max = 0;

        for (Map.Entry<Integer, int[]> entry : guards.entrySet()) {
            int[] minutes = entry.getValue();
            int totalMinutes = 0, maxTimes = 0, maxTimesMinute = 0;

            for (int i = 0; i < minutes.length; i++) {
                totalMinutes += minutes[i];
                if (minutes[i] > maxTimes) {
                    maxTimes = minutes[i];
                    maxTimesMinute = i;
                }
            }

            int valueToCompare = byTotalMinutes ? totalMinutes : maxTimes;
            if (valueToCompare > max) {
                max = valueToCompare;
                maxGuard = entry.getKey();
                maxMinute = maxTimesMinute;
            }
        }
        return maxGuard * maxMinute;
    }

    private static Map<Integer, int[]> getGuards(List<String> input) {
        input.sort(String::compareTo);
        Map<Integer, int[]> guards = new HashMap<>();
        int guard = 0, start = 0;

        for (String line : input) {
            String[] parts = line.split(" ");
            switch (parts[2]) {
                case "Guard" -> {
                    guard = Integer.parseInt(parts[3].substring(1));
                    guards.putIfAbsent(guard, new int[60]);
                }
                case "falls" -> start = Integer.parseInt(parts[1].substring(3, 5));
                case "wakes" -> {
                    int end = Integer.parseInt(parts[1].substring(3, 5));
                    for (int i = start; i < end; i++) {
                        guards.get(guard)[i]++;
                    }
                }
            }
        }
        return guards;
    }
}