package org.anne.aoc2018;

import java.util.function.BiFunction;

public enum Opcode implements BiFunction<int[], int[], int[]> {
    ADDR((before, instr) -> before[instr[1]] + before[instr[2]]),
    ADDI((before, instr) -> before[instr[1]] + instr[2]),
    MULR((before, instr) -> before[instr[1]] * before[instr[2]]),
    MULI((before, instr) -> before[instr[1]] * instr[2]),
    BANR((before, instr) -> before[instr[1]] & before[instr[2]]),
    BANI((before, instr) -> before[instr[1]] & instr[2]),
    BORR((before, instr) -> before[instr[1]] | before[instr[2]]),
    BORI((before, instr) -> before[instr[1]] | instr[2]),
    SETR((before, instr) -> before[instr[1]]),
    SETI((before, instr) -> instr[1]),
    GTIR((before, instr) -> instr[1] > before[instr[2]] ? 1 : 0),
    GTRI((before, instr) -> before[instr[1]] > instr[2] ? 1 : 0),
    GTRR((before, instr) -> before[instr[1]] > before[instr[2]] ? 1 : 0),
    EQIR((before, instr) -> instr[1] == before[instr[2]] ? 1 : 0),
    EQRI((before, instr) -> before[instr[1]] == instr[2] ? 1 : 0),
    EQRR((before, instr) -> before[instr[1]] == before[instr[2]] ? 1 : 0);

    private final BiFunction<int[], int[], Integer> operation;

    Opcode(BiFunction<int[], int[], Integer> operation) {
        this.operation = operation;
    }

    @Override
    public int[] apply(int[] before, int[] instr) {
        int[] after = before.clone();
        after[instr[3]] = operation.apply(before, instr);
        return after;
    }

    public void apply(int[] registers, int a, int b, int c) {
        registers[c] = operation.apply(registers, new int[]{0, a, b, c});
    }
}
