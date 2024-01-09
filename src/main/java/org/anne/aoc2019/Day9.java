package org.anne.aoc2019;



import org.anne.common.Day;

public class Day9 extends Day {

    public static void main(String[] args) {
        Day day = new Day9();
        day.setName("Sensor Boost");
        String input = day.readFileOneLine();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    static long part1(String input) {
        Computer computer = new Computer(input);
        computer.compute(1);
        return computer.getOutput();
    }

    static long part2(String input) {
        Computer computer = new Computer(input);
        computer.compute(2);
        return computer.getOutput();
    }
}
