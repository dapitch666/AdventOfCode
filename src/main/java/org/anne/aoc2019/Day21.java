package org.anne.aoc2019;

import org.anne.common.Day;

public class Day21 extends Day {
    public static void main(String[] args) {
        new Day21().run();
    }

    @Override
    public void execute() {
        setName("Springdroid Adventure");
        String input = readFileOneLine();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    static long part1(String input) {
        Computer computer = new Computer(input);
        String program = """
                NOT A J
                NOT B T
                OR T J
                NOT C T
                OR T J
                AND D J
                WALK
                """;
        for (char c : program.toCharArray()) {
            computer.writeInput(c);
        }
        computer.writeInput(10);
        computer.compute();
        return computer.getLastOutput();
    }

    static long part2(String input) {
        Computer computer = new Computer(input);
        String program = """
                NOT A J
                NOT B T
                OR T J
                NOT C T
                OR T J
                AND D J
                NOT E T
                NOT T T
                OR H T
                AND T J
                RUN
                """;
        for (char c : program.toCharArray()) { 
            computer.writeInput(c);
        }
        computer.writeInput(10);
        computer.compute();
        return computer.getLastOutput();
    }
}
