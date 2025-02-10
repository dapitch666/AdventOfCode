package org.anne.aoc2016;

import java.util.List;
import java.util.stream.Collectors;

public class AssemBunny {
    private final List<Instruction> instructions;
    private final int[] registers = new int[4];
    private final StringBuilder output = new StringBuilder();

    public AssemBunny(List<String> input) {
        this.instructions = input.stream().map(Instruction::parse).collect(Collectors.toList());
    }

    public void run() {
        int i = 0;
        output.setLength(0);
        while (i < instructions.size()) {
            Instruction instruction = instructions.get(i);
            switch (instruction.operation) {
                case "cpy" -> registers[instruction.arg2.charAt(0) - 'a'] = getValue(instruction.arg1);
                case "inc" -> registers[instruction.arg1.charAt(0) - 'a']++;
                case "dec" -> registers[instruction.arg1.charAt(0) - 'a']--;
                case "jnz" -> {
                    if (getValue(instruction.arg1) != 0) {
                        i += getValue(instruction.arg2) - 1;
                    }
                }
                case "tgl" -> {
                    int target = i + getValue(instruction.arg1);
                    if (target >= 0 && target < instructions.size()) {
                        instructions.set(target, toggleInstruction(instructions.get(target)));
                    }
                }
                case "out" -> {
                    output.append(getValue(instruction.arg1));
                    if (output.length() == 10) {
                        return;
                    }
                }
            }
            i++;
        }
    }

    private Instruction toggleInstruction(Instruction instruction) {
        return switch (instruction.operation) {
            case "inc" -> new Instruction("dec", instruction.arg1, null);
            case "dec", "tgl" -> new Instruction("inc", instruction.arg1, null);
            case "jnz" -> new Instruction("cpy", instruction.arg1, instruction.arg2);
            case "cpy" -> new Instruction("jnz", instruction.arg1, instruction.arg2);
            default -> throw new IllegalArgumentException("Invalid operation: " + instruction.operation);
        };
    }

    private int getValue(String arg) {
        return Character.isAlphabetic(arg.charAt(0)) ? registers[arg.charAt(0) - 'a'] : Integer.parseInt(arg);
    }

    private record Instruction(String operation, String arg1, String arg2) {
        static Instruction parse(String s) {
            String[] parts = s.split(" ");
            return new Instruction(parts[0], parts[1], parts.length == 3 ? parts[2] : null);
        }
    }

    public int getRegisterA() {
        return registers[0];
    }

    public String getOutput() {
        return output.toString();
    }

    public void setRegister(char c, int value) {
        int index = "abcd".indexOf(c);
        for (int i = 0; i < registers.length; i++) {
            registers[i] = i == index ? value : 0;
        }
    }
}
