package org.anne.aoc2019;

import org.anne.common.Day;

public class Day5 extends Day {

    public static void main(String[] args) {
        Day day = new Day5();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Sunny with a Chance of Asteroids");
        String input = this.readFileOneLine();
        this.setPart1(part1(input));
        this.setPart2(part2(input));
        this.printParts();
    }
    public static long part1(String input) {
        Computer computer = new Computer(input);
        computer.compute(1);
        return computer.getOutput();
    }

    public static long part2(String input) {
        Computer computer = new Computer(input);
        computer.compute(5);
        return computer.getOutput();
    }
}
