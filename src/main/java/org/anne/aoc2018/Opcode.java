package org.anne.aoc2018;

import java.util.function.BiConsumer;

public enum Opcode {
    ADDR((r, i) -> r[i[3]] = r[i[1]] + r[i[2]]),
    ADDI((r, i) -> r[i[3]] = r[i[1]] + i[2]),
    MULR((r, i) -> r[i[3]] = r[i[1]] * r[i[2]]),
    MULI((r, i) -> r[i[3]] = r[i[1]] * i[2]),
    BANR((r, i) -> r[i[3]] = r[i[1]] & r[i[2]]),
    BANI((r, i) -> r[i[3]] = r[i[1]] & i[2]),
    BORR((r, i) -> r[i[3]] = r[i[1]] | r[i[2]]),
    BORI((r, i) -> r[i[3]] = r[i[1]] | i[2]),
    SETR((r, i) -> r[i[3]] = r[i[1]]),
    SETI((r, i) -> r[i[3]] = i[1]),
    GTIR((r, i) -> r[i[3]] = i[1] > r[i[2]] ? 1 : 0),
    GTRI((r, i) -> r[i[3]] = r[i[1]] > i[2] ? 1 : 0),
    GTRR((r, i) -> r[i[3]] = r[i[1]] > r[i[2]] ? 1 : 0),
    EQIR((r, i) -> r[i[3]] = i[1] == r[i[2]] ? 1 : 0),
    EQRI((r, i) -> r[i[3]] = r[i[1]] == i[2] ? 1 : 0),
    EQRR((r, i) -> r[i[3]] = r[i[1]] == r[i[2]] ? 1 : 0);

    private final BiConsumer<int[], int[]> operation;

    Opcode(BiConsumer<int[], int[]> operation) {
        this.operation = operation;
    }

    public void apply(int[] registers, int[] instruction) {
        operation.accept(registers, instruction);
    }

    public void apply(int[] registers, int a, int b, int c) {
        operation.accept(registers, new int[]{0, a, b, c});
    }
}