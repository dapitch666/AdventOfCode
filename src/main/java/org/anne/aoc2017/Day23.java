package org.anne.aoc2017;

import org.anne.common.Day;

import java.util.*;
import java.util.stream.IntStream;

public class Day23 extends Day {
    public static void main(String[] args) {
        new Day23().run();
    }

    @Override
    public void execute() {
        setName("Coprocessor Conflagration");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(List<String> input) {
        List<Instruction> instructions = input.stream().map(Instruction::parse).toList();
        Map<Character, Long> registers = new HashMap<>();
        long i = 0;
        int count = 0;
        while (i < instructions.size()) {
            Instruction instruction = instructions.get((int) i);
            char target = instruction.target();
            long targetValue = Character.isDigit(target) ? Character.getNumericValue(target) : registers.getOrDefault(target, 0L);
            Long value = instruction.source() != null ? registers.getOrDefault(instruction.source(), 0L) : instruction.value();
            switch (instruction.op()) {
                case "set" -> registers.put(target, value);
                case "sub" -> registers.put(target, targetValue - value);
                case "mul" -> {
                    registers.put(target, targetValue * value);
                    count++;
                }
                case "jnz" -> {
                    if (targetValue != 0) {
                        i += value - 1;
                    }
                }
            }
            i++;
        }
        return count;
    }

    public static long part2(List<String> input) {
        // Figured out that the program is counting non-prime numbers between b and b + 17000 by stepping 17
        int b = input.stream()
                .filter(s -> s.startsWith("set b"))
                .map(s -> Integer.parseInt(s.split(" ")[2]))
                .findFirst().orElseThrow();
        return IntStream.iterate(b * 100 + 100000, i -> i + 17)
                .limit(17000 / 17 + 1)
                .filter(i -> !isPrime(i))
                .count();
    }

    private static boolean isPrime(int n) {
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }

}
