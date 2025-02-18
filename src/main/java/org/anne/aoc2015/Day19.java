package org.anne.aoc2015;

import org.anne.common.Day;

import java.util.*;

public class Day19 extends Day {
    public static void main(String[] args) {
        new Day19().run();
    }

    @Override
    public void execute() {
        setName("Medicine for Rudolph");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(List<String> input) {
        List<String[]> rules = input.stream().limit(input.indexOf("")).map(line -> line.split(" => ")).toList();
        String molecule = input.get(input.indexOf("") + 1);
        Set<String> molecules = new HashSet<>();
        for (String[] rule : rules) {
            int index = 0;
            while (index < molecule.length()) {
                index = molecule.indexOf(rule[0], index);
                if (index == -1) break;
                molecules.add(molecule.substring(0, index) + rule[1] + molecule.substring(index + rule[0].length()));
                index++;
            }
        }
        return molecules.size();
    }

    public static int part2(List<String> input) {
        List<String[]> rules = input.stream().limit(input.indexOf("")).map(line -> line.split(" => ")).toList();
        String molecule = input.get(input.indexOf("") + 1);
        int steps = 0;
        while (!molecule.equals("e")) {
            for (String[] rule : rules) {
                if (molecule.contains(rule[1])) {
                    int index = molecule.lastIndexOf(rule[1]);
                    molecule = molecule.substring(0, index) + rule[0] + molecule.substring(index + rule[1].length());
                    steps++;
                }
            }
        }
        return steps;
    }
}
