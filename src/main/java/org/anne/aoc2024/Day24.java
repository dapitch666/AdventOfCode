package org.anne.aoc2024;

import org.anne.common.Day;
import java.util.*;
import java.util.stream.Collectors;

public class Day24 extends Day {
    public static void main(String[] args) {
        new Day24().run();
    }

    @Override
    public void execute() {
        setName("Crossed Wires");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static long part1(List<String> input) {
        Map<String, String> registers = getRegisters(input);
        return registers.keySet().stream()
                .filter(s -> s.startsWith("z"))
                .sorted(Comparator.reverseOrder())
                .mapToLong(name -> evaluate(registers, name))
                .reduce(0L, (current, value) -> current * 2 + value);
    }

    public static String part2(List<String> input) {
        Map<String, String> registers = getRegisters(input);
        List<String> swaps = new ArrayList<>();
        int index = 0;
        String current = "";
        while (registers.containsKey(String.format("x%02d", index))) {
            String x = String.format("x%02d", index);
            String y = String.format("y%02d", index);
            String z = String.format("z%02d", index);
            if (index == 0) {
                current = findExpression(registers, x, "AND", y);
            } else {
                String xor = findExpression(registers, x, "XOR", y);
                String and = findExpression(registers, x, "AND", y);
                String next = findExpression(registers, xor, "XOR", current);
                if (next == null) {
                    swaps.addAll(List.of(xor, and));
                    swapRegisters(registers, xor, and);
                    index = 0;
                    continue;
                }
                if (!next.equals(z)) {
                    swaps.addAll(List.of(next, z));
                    swapRegisters(registers, next, z);
                    index = 0;
                    continue;
                }
                next = findExpression(registers, xor, "AND", current);
                current = findExpression(registers, and, "OR", next);
            }
            index++;
        }
        return swaps.stream().sorted().collect(Collectors.joining(","));
    }

    private static String findExpression(Map<String, String> registers, String op1, String op, String op2) {
        return registers.entrySet().stream()
                .filter(entry -> {
                    String value = entry.getValue();
                    return value.equals(op1 + " " + op + " " + op2) || value.equals(op2 + " " + op + " " + op1);
                })
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    private static void swapRegisters(Map<String, String> registers, String register1, String register2) {
        String temp = registers.put(register1, registers.get(register2));
        registers.put(register2, temp);
    }

    private static Map<String, String> getRegisters(List<String> input) {
        Map<String, String> registers = new HashMap<>();
        for (String line : input.subList(0, input.indexOf(""))) {
            String[] parts = line.split(": ");
            registers.put(parts[0], parts[1]);
        }
        for (String line : input.subList(input.indexOf("") + 1, input.size())) {
            String[] parts = line.split(" -> ");
            registers.put(parts[1], parts[0]);
        }
        return registers;
    }

    private static int evaluate(Map<String, String> registers, String name) {
        String value = registers.get(name);
        if (value.matches("-?\\d+")) {
            return Integer.parseInt(value);
        }
        String[] parts = value.split(" ");
        int op1 = evaluate(registers, parts[0]);
        int op2 = evaluate(registers, parts[2]);
        return switch (parts[1]) {
            case "XOR" -> op1 ^ op2;
            case "AND" -> op1 & op2;
            default -> op1 | op2;
        };
    }
}