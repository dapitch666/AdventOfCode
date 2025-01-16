package org.anne.aoc2018;

public record Instruction(Opcode opcode, int a, int b, int c) {
    void apply(int[] registers) {
        opcode.apply(registers, a, b, c);
    }
    static Instruction fromString(String line) {
        String[] parts = line.split(" ");
        return new Instruction(
                Opcode.valueOf(parts[0].toUpperCase()),
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[2]),
                Integer.parseInt(parts[3])
        );
    }
}