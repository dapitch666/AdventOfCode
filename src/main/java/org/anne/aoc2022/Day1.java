package org.anne.aoc2022;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day1 extends Day {
    public static void main(String[] args) {
        new Day1().run();
    }

    @Override
    public void execute() {
        setName("Calorie Counting");
        List<String> input = readFile();
        List<Integer> elves = getElves(input);
        setPart1(part1(elves));
        setPart2(part2(elves));
    }

    public static int part1(List<Integer> elves) {
        return elves.get(elves.size() - 1);
    }

    public static int part2(List<Integer> elves) {
        return elves.get(elves.size() - 1) + elves.get(elves.size() - 2) + elves.get(elves.size() - 3);
    }

    public static List<Integer> getElves(List<String> input) {
        List<Integer> elves = new ArrayList<>();
        int sum = 0;
        for (String s : input) {
            if (s.isEmpty()) {
                elves.add(sum);
                sum = 0;
            } else {
                sum += Integer.parseInt(s);
            }
        }
        elves.add(sum);
        elves.sort(Comparator.comparingInt(Integer::intValue));
        return elves;
    }

}
