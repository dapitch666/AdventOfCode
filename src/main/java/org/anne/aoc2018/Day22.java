package org.anne.aoc2018;

import org.anne.common.Day;

import java.util.List;

public class Day22 extends Day {
    public static void main(String[] args) {
        new Day22().run();
    }

    @Override
    public void execute() {
        setName("Mode Maze");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(List<String> input) {
        return 0;
    }

    public static int part2(List<String> input) {
        return 0;
    }
}
