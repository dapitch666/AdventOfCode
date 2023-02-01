package org.anne.aoc2020;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Day1 extends Day {

    public static void main(String[] args) {
        Day day = new Day1();
        List<Integer> input = day.readFileAsInts();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    static int part1(List<Integer> input) {
        List<Integer> entryList = new LinkedList<>(input);
        while (entryList.size() > 0) {
            int entry = entryList.get(0);
            entryList.remove(0);
            for (int i : entryList) {
                if (entry + i == 2020) {
                    return entry * i;
                }
            }
        }
        return 0;
    }

    static int part2(List<Integer> input) {
        List<Integer> entryList = new LinkedList<>(input);
        while (entryList.size() > 0) {
            int first = entryList.get(0);
            entryList.remove(0);
            List<Integer> entryListCopy = new ArrayList<>(entryList);
            while (entryListCopy.size() > 0) {
                int second = entryListCopy.get(0);
                entryListCopy.remove(0);
                int somme = first + second;
                if (somme < 2020) {
                    for (int i : entryListCopy) {
                        if (somme + i == 2020) {
                            return first * second * i;
                        }
                    }
                }
            }
        }
        return 0;
    }
}
