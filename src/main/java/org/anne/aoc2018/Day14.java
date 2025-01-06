package org.anne.aoc2018;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day14 extends Day {
    public static void main(String[] args) {
        new Day14().run();
    }

    @Override
    public void execute() {
        setName("Chocolate Charts");
        String input = readFileOneLine();
        setPart1(part1(Integer.parseInt(input)));
        setPart2(part2(input));
        printParts();
    }

    public static String part1(int input) {
        List<Integer> scores = new ArrayList<>(List.of(3, 7));
        int elf1 = 0, elf2 = 1;
        while (scores.size() < input + 10) {
            addScores(scores, elf1, elf2);
            elf1 = (elf1 + scores.get(elf1) + 1) % scores.size();
            elf2 = (elf2 + scores.get(elf2) + 1) % scores.size();
        }
        return scores.subList(input, input + 10).stream()
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    public static int part2(String inputStr) {
        int[] input = inputStr.chars().map(c -> c - '0').toArray();
        List<Integer> scores = new ArrayList<>(List.of(3, 7));
        int index = 0, position = 0, elf1 = 0, elf2 = 1;
        while (true) {
            addScores(scores, elf1, elf2);
            elf1 = (elf1 + scores.get(elf1) + 1) % scores.size();
            elf2 = (elf2 + scores.get(elf2) + 1) % scores.size();

            while (index + position < scores.size()) {
                if (input[position] == scores.get(index + position)) {
                    if (position == input.length - 1) {
                        return index;
                    }
                    position++;
                } else {
                    position = 0;
                    index++;
                }
            }
        }
    }

    private static void addScores(List<Integer> scores, int elf1, int elf2) {
        int sum = scores.get(elf1) + scores.get(elf2);
        if (sum >= 10) {
            scores.add(1);
            scores.add(sum % 10);
        } else {
            scores.add(sum);
        }
    }
}
