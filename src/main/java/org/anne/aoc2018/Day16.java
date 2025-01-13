package org.anne.aoc2018;

import org.anne.common.Day;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Day16 extends Day {
    public static void main(String[] args) {
        new Day16().run();
    }

    @Override
    public void execute() {
        setName("Chronal Classification");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(List<String> input) {
        Opcode[] opcodes = Opcode.values();
        int count = 0;
        for (int i = 0; i < input.size(); i += 4) {
            if (input.get(i).isEmpty()) break;
            int[] before = getArray(input.get(i));
            int[] instruction = getArray(input.get(i + 1));
            int[] after = getArray(input.get(i + 2));
            if (Arrays.stream(opcodes)
                    .filter(opcode -> Arrays.equals(opcode.apply(before, instruction), after))
                    .count() >= 3) {
                count++;
            }
        }
        return count;
    }

    public static int part2(List<String> input) {
        List<Opcode> opcodes = Arrays.asList(Opcode.values());
        Map<Integer, List<Opcode>> options = new HashMap<>();

        int splitIndex = 0;
        for (int i = 0; i < input.size(); i += 4) {
            if (input.get(i).isEmpty()) {
                splitIndex = i;
                break;
            }
            int[] before = getArray(input.get(i));
            int[] instruction = getArray(input.get(i + 1));
            int[] after = getArray(input.get(i + 2));

            options.put(instruction[0], opcodes.stream()
                    .filter(fn -> Arrays.equals(fn.apply(before, instruction), after))
                    .collect(Collectors.toList()));
        }

        while (options.values().stream().anyMatch(list -> list.size() > 1)) {
            options.values().stream()
                    .filter(opcodeList -> opcodeList.size() == 1)
                    .map(List::getFirst)
                    .forEach(uniqueFn -> options.forEach((code, list) -> {
                        if (list.size() > 1) {
                            list.remove(uniqueFn);
                        }
                    }));
        }

        int[] registers = {0, 0, 0, 0};
        for (String line : input.subList(splitIndex + 2, input.size())) {
            int[] instruction = getArray(line);
            registers = options.get(instruction[0]).getFirst().apply(registers, instruction);
        }
        return registers[0];
    }

    private static int[] getArray(String s) {
        if (s.endsWith("]")) {
            return s.chars().filter(Character::isDigit).map(Character::getNumericValue).toArray();
        }
        return Arrays.stream(s.split(" ")).map(Integer::parseInt).mapToInt(Integer::intValue).toArray();
    }

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
    }
}
