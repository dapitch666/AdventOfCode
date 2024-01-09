package org.anne.aoc2020;

import org.anne.common.Day;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Day9 extends Day {
    private static final int STEP = 25;

    public static void main(String[] args) {
        Day day = new Day9();
        day.setName("Encoding Error");
        List<Long> input = day.readFileAsLongs();
        day.setPart1(part1(input, STEP));
        day.setPart2(part2(input, STEP));
        day.printParts();
    }

    public static long part1(List<Long> input, int step) {
        return findWeakness(input, step);
    }

    public static long part2(List<Long> input, int step) {
        long weakNumber = findWeakness(input, step);
        SortedSet<Long> encryptionWeakness = findEncryptionWeakness(input, weakNumber);
        return encryptionWeakness.first() + encryptionWeakness.last();
    }

    private static SortedSet<Long> findEncryptionWeakness(List<Long> longList, long weakNumber) {
        SortedSet<Long> resultSet = new TreeSet<>();
        for (int i = 0; i < longList.size(); i++) {
            long sum = longList.get(i);
            resultSet.add(sum);
            int j = i+1;
            while (sum < weakNumber) {
                long l = longList.get(j);
                resultSet.add(l);
                sum += l;
                j++;
            }
            if (sum == weakNumber) {
                return resultSet;
            } else {
                resultSet.clear();
            }
        }
        return resultSet;
    }

    private static Long findWeakness(List<Long> longList, int step) {
        for (int i = step ; i < longList.size(); i++) {
            List<Long> entryList = longList.stream().skip(i - step).limit(step).collect(Collectors.toList());
            long sum = longList.get(i);
            if (!validateSum(entryList, sum)) {
                return sum;
            }
        }
        return 0L;
    }

    private static boolean validateSum(List<Long> entryList, long sum) {
        while (!entryList.isEmpty()) {
            long entry = entryList.get(0);
            entryList.remove(0);
            for (long i : entryList) {
                if (entry + i == sum) {
                    return true;
                }
            }
        }
        return false;
    }
}
