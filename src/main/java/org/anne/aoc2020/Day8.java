package org.anne.aoc2020;

import org.anne.common.Day;

import java.util.List;
import java.util.stream.Collectors;

public class Day8 extends Day {
    static int accumulator;

    public static void main(String[] args) {
        Day day = new Day8();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Handheld Halting");
        List<String> input = this.readFile();
        this.setPart1(part1(input));
        this.setPart2(part2(input));
        this.printParts();
    }

    public static int part1(List<String> input) {
        List<Instruction> bootCode = input.stream().map(Instruction::new).collect(Collectors.toList());
        accumulator = 0;
        run(bootCode, -1L);
        return accumulator;
    }

    public static int part2(List<String> input) {
        List<Instruction> bootCode = input.stream().map(Instruction::new).collect(Collectors.toList());
        long permutations = bootCode.stream().filter(i -> !i.getOperation().equals("acc")).count();

        for (long i = 0L; i < permutations; i++) {
            accumulator = 0;
            bootCode.forEach(Instruction::init);
            if (run(bootCode, i)) {
                break;
            }
        }
        return accumulator;
    }

    private static boolean run(List<Instruction> bootCode, long change) {
        int i = 0;
        int countJmpNop = 0;
        while (i < bootCode.size()) {
            Instruction instruction = bootCode.get(i);
            if (instruction.isUsed()) {
                return false;
            }
            instruction.use();
            String operation = instruction.getOperation();
            if (!operation.equals("acc")) {
                countJmpNop++;
                if (countJmpNop == change) {
                    operation = operation.equals("jmp") ? "nop" : "jmp";
                }
            }
            switch (operation) {
                case "acc" -> {
                    accumulator += instruction.getArgument();
                    i++;
                }
                case "jmp" -> i += instruction.getArgument();
                case "nop" -> i++;
            }
        }
        return true;
    }

    static class Instruction {
        private final String operation;
        private final int argument;
        private boolean used;

        public Instruction(String line) {
            String[] lineArray = line.split(" ");
            this.operation = lineArray[0];
            this.argument = Integer.parseInt(lineArray[1]);
            this.used = false;
        }

        public String getOperation() {
            return operation;
        }

        public int getArgument() {
            return argument;
        }

        public boolean isUsed() {
            return used;
        }

        public void use() {
            this.used = true;
        }

        public void init() {
            this.used = false;
        }
    }
}
