package org.anne.aoc2019;

import org.anne.common.Day;

public class Day5 extends Day {
    public static void main(String[] args) {
        new Day5().run();
    }

    @Override
    public void execute() {
        setName("Sunny with a Chance of Asteroids");
        String input = readFileOneLine();
        setPart1(part1(input));
        setPart2(part2(input));
    }
    public static long part1(String input) {
        Computer computer = new Computer(input);
        computer.compute(1);
        return computer.getLastOutput();
    }

    public static long part2(String input) {
        Computer computer = new Computer(input);
        computer.compute(5);
        return computer.getOutput();
    }
}
