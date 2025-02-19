package org.anne.aoc2015;

import org.anne.common.Day;

import java.util.List;

public class Day23 extends Day {
    public static void main(String[] args) {
        new Day23().run();
    }

    @Override
    public void execute() {
        setName("Opening the Turing Lock");
        List<String> input = readFile();
        setPart1(part1(input, 'b'));
        setPart2(part2(input, 'b'));
    }

    public static int part1(List<String> input, char register) {
        return compute(input, new int[2], register);
    }

    public static int part2(List<String> input, char register) {
        return compute(input, new int[]{1, 0}, register);
    }

    private static int compute(List<String> input, int[] registers, char register) {
        List<Instruction> instructions = input.stream().map(Instruction::fromString).toList();
        int index = 0;
        while (index >= 0 && index < instructions.size()) {
            Instruction instruction = instructions.get(index);
            int reg = instruction.register - 'a';
            switch (instruction.operation) {
                case "hlf" -> registers[reg] /= 2;
                case "tpl" -> registers[reg] *= 3;
                case "inc" -> registers[reg]++;
                case "jmp" -> index += instruction.offset - 1;
                case "jie" -> {
                    if (registers[reg] % 2 == 0) index += instruction.offset - 1;
                }
                case "jio" -> {
                    if (registers[reg] == 1) index += instruction.offset - 1;
                }
            }
            index++;

        }
        return registers[register - 'a'];
    }

    record Instruction(String operation, char register, int offset) {
        public static Instruction fromString(String s) {
            String[] parts = s.split("[ ,]+");
            return new Instruction(parts[0], parts[1].charAt(0), parts.length == 3 ? Integer.parseInt(parts[2]) : Character.isAlphabetic(parts[1].charAt(0)) ? 0 : Integer.parseInt(parts[1]));
        }
    }
}
