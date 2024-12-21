package org.anne.aoc2020;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Day1 extends Day {

    public static void main(String[] args) {
        Day day = new Day1();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Report Repair");
        List<Integer> input = this.readFileAsInts();
        this.setPart1(part1(input));
        this.setPart2(part2(input));
        this.printParts();
    }

    static int part1(List<Integer> input) {
        List<Integer> entryList = new LinkedList<>(input);
        while (!entryList.isEmpty()) {
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
        while (!entryList.isEmpty()) {
            int first = entryList.get(0);
            entryList.remove(0);
            List<Integer> entryListCopy = new ArrayList<>(entryList);
            while (!entryListCopy.isEmpty()) {
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
