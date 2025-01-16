package org.anne.aoc2018;

import org.anne.common.Day;

import java.util.*;

public class Day21 extends Day {
    public static void main(String[] args) {
        new Day21().run();
    }

    @Override
    public void execute() {
        setName("Chronal Conversion");
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

        while (pointer != 28) { // 28 is the only instruction that compares register 3 to register 0
            registers[boundRegister] = pointer;
            instructions.get(pointer).apply(registers);
            pointer = registers[boundRegister] + 1;
        }
        return registers[3];
    }

    public static int part2(List<String> input) {
        // reverse engineered from input
        int initialRegister3 = input.stream()
                .skip(1)
                .filter(s -> s.startsWith("seti"))
                .mapToInt(s -> Integer.parseInt(s.split(" ")[1]))
                .max().orElseThrow();
        return getAllHaltValues(initialRegister3).getLast();
    }

    private static List<Integer> getAllHaltValues(int initialRegister3) {
        Set<Integer> values = new LinkedHashSet<>();
        int register3 = 0;
        while (true) {
            int register4 = register3 | 65536;
            register3 = initialRegister3;
            while (true) {
                register3 = (((register3 + (register4 & 255)) & 16777215) * 65899) & 16777215;
                if (register4 < 256) {
                    if (!values.add(register3)) return new ArrayList<>(values);
                    break;
                }
                register4 = register4 >> 8;
            }
        }
    }
}
