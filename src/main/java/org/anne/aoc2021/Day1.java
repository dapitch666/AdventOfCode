package org.anne.aoc2021;

import org.anne.common.Day;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Day1 extends Day {

    public static void main(String[] args) {
        Day day = new Day1();
        List<Integer> input = day.readFileAsInts();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    public static int part1(List<Integer> input) {
        return numberOfIncrease(input);
    }

    public static int part2(List<Integer> input) {
        return numberOfIncrease(sliding(input, 3));
    }

    public static int numberOfIncrease(List<Integer> list) {
        int i = 0;
        int current = list.get(0);
        for (int next : list) {
            if (next > current) {
                i++;
            }
            current = next;
        }
        return i;
    }

    public static List<Integer> sliding(List<Integer> list, int size) {
        if(size > list.size())
            return Collections.emptyList();
        return IntStream.range(0, list.size() - size + 1)
                .mapToObj(start -> list.subList(start, start + size)
                        .stream()
                        .reduce(0, Integer::sum))
                .toList();
    }
}
