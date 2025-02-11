package org.anne.aoc2017;

import org.anne.common.Day;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day8 extends Day {
    public static void main(String[] args) {
        new Day8().run();
    }

    @Override
    public void execute() {
        setName("I Heard You Like Registers");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(List<String> input) {
        return processInstructions(input, false);
    }

    public static int part2(List<String> input) {
        return processInstructions(input, true);
    }

    private static int processInstructions(List<String> input, boolean trackMax) {
        Map<String, Integer> registers = new HashMap<>();
        int max = 0;
        for (String line : input) {
            String[] parts = line.split(" ");
            String register = parts[0];
            int value = registers.getOrDefault(register, 0);
            int conditionRegisterValue = registers.getOrDefault(parts[4], 0);
            if (conditionIsTrue(parts[5], conditionRegisterValue, Integer.parseInt(parts[6]))) {
                int newValue = value + (parts[1].equals("inc") ? Integer.parseInt(parts[2]) : -Integer.parseInt(parts[2]));
                registers.put(register, newValue);
                if (trackMax) {
                    max = Math.max(max, newValue);
                }
            }
        }
        return trackMax ? max : registers.values().stream().mapToInt(Integer::intValue).max().orElseThrow();
    }

    private static boolean conditionIsTrue(String condition, int i, int conditionValue) {
        return switch (condition) {
            case ">" -> i > conditionValue;
            case "<" -> i < conditionValue;
            case ">=" -> i >= conditionValue;
            case "<=" -> i <= conditionValue;
            case "==" -> i == conditionValue;
            case "!=" -> i != conditionValue;
            default -> false;
        };
    }
}