package org.anne.aoc2017;

import org.anne.common.Day;

import java.util.*;

public class Day18 extends Day {
    public static void main(String[] args) {
        new Day18().run();
    }

    @Override
    public void execute() {
        setName("Duet");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static long part1(List<String> input) {
        List<Instruction> instructions = input.stream().map(Instruction::parse).toList();
        Program program = new Program(0);
        while (!program.wait) {
            program.execute(instructions, null);
        }
        return program.result;
    }

    public static long part2(List<String> input) {
        List<Instruction> instructions = input.stream().map(Instruction::parse).toList();
        Program program0 = new Program(0);
        Program program1 = new Program(1);
        while (!program0.wait || !program1.wait) {
            program0.execute(instructions, program1);
            program1.execute(instructions, program0);
        }
        return program1.result;
    }

    private static class Program {
        Map<Character, Long> registers = new HashMap<>();
        Deque<Long> queue = new ArrayDeque<>();
        long i = 0;
        boolean wait = false;
        long result = 0;

        public Program(int id) {
            registers.put('p', (long) id);
        }

        public void execute(List<Instruction> instructions, Program other) {
            while (!wait && i < instructions.size()) {
                Instruction instruction = instructions.get((int) i);
                char target = instruction.target();
                long targetValue = Character.isDigit(target) ? Character.getNumericValue(target) : registers.getOrDefault(target, 0L);
                Long value = instruction.source() != null ? registers.getOrDefault(instruction.source(), 0L) : instruction.value();
                switch (instruction.op()) {
                    case "snd" -> snd(targetValue, other);
                    case "set" -> registers.put(target, value);
                    case "add" -> registers.put(target, targetValue + value);
                    case "mul" -> registers.put(target, targetValue * value);
                    case "mod" -> registers.put(target, targetValue % value);
                    case "rcv" -> rcv(target);
                    case "jgz" -> jgz(targetValue, value);
                }
                if (!wait) i++;
            }
        }

        private void snd(long value, Program other) {
            if (other == null) {
                result = value;
            } else {
                other.queue.add(value);
                other.wait = false;
                result++;
            }
        }

        private void rcv(char target) {
            if (queue.isEmpty()) {
                wait = true;
            } else {
                registers.put(target, queue.poll());
            }
        }

        private void jgz(long targetValue, Long value) {
            if (targetValue > 0) {
                i += value - 1;
            }
        }
    }


}
