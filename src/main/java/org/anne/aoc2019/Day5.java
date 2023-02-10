package org.anne.aoc2019;

import org.anne.common.Day;

public class Day5 extends Day {

    public static void main(String[] args) {
        Day day = new Day5();
        String input = day.readFileOneLine();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }
    public static long part1(String input) {
        Computer computer = new Computer(input);
        computer.compute(1);
        return computer.getLastOutput();
    }

    public static long part2(String input) {
        Computer computer = new Computer(input);
        computer.compute(5);
        return computer.getLastOutput();
    }
}
