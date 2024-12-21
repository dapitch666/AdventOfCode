package org.anne.aoc2024;

import org.anne.common.Day;

import java.util.*;
import java.util.stream.Collectors;

public class Day17 extends Day {
    public static void main(String[] args) {
        Day day = new Day17();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Chronospatial Computer");
        List<String> input = this.readFile();
        this.setPart1(part1(input));
        this.setPart2(part2(input));
        this.printParts();
    }

    public static String part1(List<String> input) {
        int registerA = getRegisterValue(input, 0);
        int registerB = getRegisterValue(input, 1);
        int registerC = getRegisterValue(input, 2);
        List<Integer> instructions = getInstructions(input);

        Computer computer = new Computer(registerA, registerB, registerC, instructions);
        return computer.getOutput().stream()
                .map(String::valueOf).collect(Collectors.joining(","));
    }

    public static long part2(List<String> input) {
        int registerB = getRegisterValue(input, 1);
        int registerC = getRegisterValue(input, 2);
        List<Integer> instructions = getInstructions(input);

        Set<Long> possibleASet = new HashSet<>();
        possibleASet.add(0L);

        int programSize = instructions.size();

        for (int i = 1; i <= programSize; i++) {
            Set<Long> newPossibleASet = new HashSet<>();
            for (long possibleA : possibleASet) {
                for (long registerA = possibleA; registerA < possibleA + 8; registerA++) {
                    Computer computer = new Computer(registerA, registerB, registerC, instructions);
                    if (isValidOutput(computer.getOutput(), instructions, i, programSize)) {
                        newPossibleASet.add(i < programSize ? registerA << 3 : registerA);
                    }
                }
            }
            possibleASet = newPossibleASet;
        }
        return possibleASet.stream().mapToLong(l -> l).min().orElse(0L);
    }

    private static boolean isValidOutput(List<Integer> output, List<Integer> instructions, int i, int programSize) {
        int outputSize = output.size();
        for (int j = i; j > 0; j--) {
            if (outputSize < i || !Objects.equals(instructions.get(programSize - i), output.get(outputSize - i))) {
                return false;
            }
        }
        return true;
    }

    private static int getRegisterValue(List<String> input, int index) {
        return Integer.parseInt(input.get(index).split(" ")[2]);
    }

    private static List<Integer> getInstructions(List<String> input) {
        return Arrays.stream(input.get(4).split(": ")[1].split(","))
                .map(Integer::parseInt).toList();
    }

    private record Computer(long a, int b, int c, List<Integer> instructions) {
        List<Integer> getOutput() {
            long registerA = a;
            long registerB = b;
            long registerC = c;
            List<Integer> output = new ArrayList<>();
            int pointer = 0;
            while (pointer < instructions.size()) {
                int opcode = instructions.get(pointer);
                int operand = instructions.get(pointer + 1);
                long combo = switch (operand) {
                    case 4 -> registerA;
                    case 5 -> registerB;
                    case 6 -> registerC;
                    default -> operand;
                };
                switch (opcode) {
                    case 0 -> registerA /= (long) Math.pow(2, combo);
                    case 1 -> registerB ^= operand;
                    case 2 -> registerB = combo % 8;
                    case 3 -> pointer = (registerA != 0L) ? operand - 2 : pointer;
                    case 4 -> registerB ^= registerC;
                    case 5 -> output.add((int) (combo % 8));
                    case 6 -> registerB = (long) (registerA / Math.pow(2, combo));
                    case 7 -> registerC = (long) (registerA / Math.pow(2, combo));
                }
                pointer += 2;
            }
            return output;
        }
    }
}
