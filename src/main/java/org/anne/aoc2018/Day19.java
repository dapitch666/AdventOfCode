package org.anne.aoc2018;

import org.anne.common.Day;

import java.util.*;
import java.util.stream.IntStream;

public class Day19 extends Day {
    public static void main(String[] args) {
        new Day19().run();
    }

    @Override
    public void execute() {
        setName("Go With The Flow");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(List<String> input) {
        int pointer = 0;
        int boundRegister = Integer.parseInt(input.getFirst().split(" ")[1]);
        int[] registers = new int[6];
        var instructions = input.subList(1, input.size()).stream().map(Instruction::fromString).toList();

        while (pointer < input.size() - 1) {
            registers[boundRegister] = pointer;
            instructions.get(pointer).apply(registers);
            pointer = registers[boundRegister] + 1;
        }
        return registers[0];
    }

    public static int part2(List<String> input) {
        int pointer = 0;
        int boundRegister = Integer.parseInt(input.getFirst().split(" ")[1]);
        int[] registers = {1, 0, 0, 0, 0, 0};
        var instructions = input.subList(1, input.size()).stream().map(Instruction::fromString).toList();

        // Looking at the registers for the 1000 first iterations,
        // we see that the value of register 2 doesn't change after register 0 becomes 0
        while (registers[0] > 0) {
            registers[boundRegister] = pointer;
            instructions.get(pointer).apply(registers);
            pointer = registers[boundRegister] + 1;
        }
        return sumOfFactors(registers[2]);
    }

    static int sumOfFactors(int n) {
        return IntStream.range(1, n + 1)
                .filter(x -> n % x == 0)
                .sum();
    }
}
