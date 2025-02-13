package org.anne.aoc2015;

import org.anne.common.Day;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class Day7 extends Day {
    public static void main(String[] args) {
        new Day7().run();
    }

    @Override
    public void execute() {
        setName("Some Assembly Required");
        List<String> input = readFile();
        setPart1(part1(input, "a"));
        setPart2(part2(input, "a"));
    }

    public static int part1(List<String> input, String wire) {
        return getWire(getOperations(input), wire);
    }

    public static int part2(List<String> input, String wire) {
        List<Operation> operations = getOperations(input);
        int b = getWire(operations, wire);
        operations.remove(operations.stream().filter(op -> op.variable.equals("b")).findFirst().orElseThrow());
        operations.addFirst(new Operation(new String[]{String.valueOf(b)}, "b"));
        return getWire(operations, wire);
    }

    private static int getWire(List<Operation> operations, String wire) {
        Map<String, Integer> wires = new HashMap<>();
        while (true) {
            for (Operation operation : operations) {
                if (wires.containsKey(operation.variable)) continue;

                Optional<Integer> result = operation.execute(wires);
                if (result.isPresent()) {
                    if (operation.variable.equals(wire)) {
                        return result.get();
                    }
                    wires.put(operation.variable, result.get());
                }
            }
        }
    }

    private static List<Operation> getOperations(List<String> input) {
        return input.stream()
                .map(s -> s.split(" -> "))
                .map(parts -> new Operation(parts[0].split(" "), parts[1]))
                .sorted()
                .collect(Collectors.toList());
    }

    private static Integer compute(String operator, int a, int b) {
        return switch (operator) {
            case "AND" -> a & b;
            case "OR" -> a | b;
            case "LSHIFT" -> a << b;
            case "RSHIFT" -> a >> b;
            default -> throw new IllegalArgumentException("Unknown operator: " + operator);
        };
    }

    private static Optional<Integer> getValue(Map<String, Integer> wires, String operand) {
        if (operand.matches("\\d+")) {
            return Optional.of(Integer.parseInt(operand));
        }
        return Optional.ofNullable(wires.get(operand));
    }

record Operation(String[] expression, String variable) implements Comparable<Operation> {
    private boolean isValueAssignment() {
        return expression.length == 1 && expression[0].matches("\\d+");
    }

    @Override
    public int compareTo(@NotNull Operation other) {
        return Comparator.comparing(Operation::isValueAssignment).reversed()
                .thenComparing(Operation::variable)
                .compare(this, other);
    }

    public Optional<Integer> execute(Map<String, Integer> wires) {
        return switch (expression.length) {
            case 1 -> getValue(wires, expression[0]);
            case 2 -> getValue(wires, expression[1]).map(integer -> ~integer & 0xFFFF);
            case 3 -> {
                Optional<Integer> a = getValue(wires, expression[0]);
                Optional<Integer> b = getValue(wires, expression[2]);
                if (a.isEmpty() || b.isEmpty()) {
                    yield Optional.empty();
                }
                yield Optional.of(compute(expression[1], a.get(), b.get()));
            }
            default -> throw new IllegalArgumentException("Unknown operator " + expression[1]);
        };
    }
}}
