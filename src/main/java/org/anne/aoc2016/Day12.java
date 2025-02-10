package org.anne.aoc2016;

import org.anne.common.Day;

import java.util.List;

public class Day12 extends Day {
    public static void main(String[] args) {
        new Day12().run();
    }

    @Override
    public void execute() {
        setName("Leonardo's Monorail");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(List<String> input) {
        Computer computer = new Computer(input, new int[]{0, 0, 0, 0});
        return computer.execute();
    }

    public static int part2(List<String> input) {
        Computer computer = new Computer(input, new int[]{0, 0, 1, 0});
        return computer.execute();
    }
}