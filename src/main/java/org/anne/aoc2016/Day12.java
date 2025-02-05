package org.anne.aoc2016;

import org.anne.common.Day;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return executeInstructions(input, 0);
    }

    public static int part2(List<String> input) {
        return executeInstructions(input, 1);
    }

    private static int executeInstructions(List<String> input, int c) {
        List<Instruction> instructions = input.stream().map(Instruction::parse).toList();
        Map<Character, Integer> registers = new HashMap<>(Map.of('a', 0, 'b', 0, 'c', c, 'd', 0));
        int i = 0;
        while (i < instructions.size()) {
            Instruction instruction = instructions.get(i);
            switch (instruction.operation) {
                case "cpy" -> registers.put(instruction.arg2.charAt(0), getValue(registers, instruction.arg1));
                case "inc" -> registers.put(instruction.arg1.charAt(0), registers.get(instruction.arg1.charAt(0)) + 1);
                case "dec" -> registers.put(instruction.arg1.charAt(0), registers.get(instruction.arg1.charAt(0)) - 1);
                case "jnz" -> {
                    if (getValue(registers, instruction.arg1) != 0) {
                        i += Integer.parseInt(instruction.arg2) - 1;
                    }
                }
            }
            i++;
        }
        return registers.get('a');
    }

    private static int getValue(Map<Character, Integer> registers, String arg) {
        return Character.isAlphabetic(arg.charAt(0)) ? registers.get(arg.charAt(0)) : Integer.parseInt(arg);
    }

    record Instruction(String operation, String arg1, String arg2) {
        static Instruction parse(String s) {
            String[] parts = s.split(" ");
            return new Instruction(parts[0], parts[1], parts.length == 3 ? parts[2] : null);
        }
    }
}