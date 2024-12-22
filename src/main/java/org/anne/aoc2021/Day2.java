package org.anne.aoc2021;

import org.anne.common.Day;

import java.util.List;

public class Day2 extends Day {

    public static void main(String[] args) {
        new Day2().run();
    }

    @Override
    public void execute() {
        setName("Dive!");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    private static long part1(List<String> input) {
        return calculatePosition(input, 1);
    }

    private static long part2(List<String> input) {
        return calculatePosition(input, 2);
    }

    public static long calculatePosition(List<String> commands, int part) {
        long horizontalPosition = 0;
        long depth = 0;
        long aim = 0;
        for (String line: commands) {
            String[] command = line.split(" ");
            long value = Integer.parseInt(command[1]);
            switch (command[0]) {
                case "forward" -> {
                    horizontalPosition += value;
                    if (part == 2) {
                        depth += aim * value;
                    }
                }
                case "up" -> {
                    if (part == 1) depth -= value; else aim -= value;
                }
                case "down" -> {
                    if (part == 1) depth += value; else aim += value;
                }
                default -> throw new IllegalStateException("Unexpected value: " + command[0]);
            }
        }
        return horizontalPosition * depth;
    }
}
