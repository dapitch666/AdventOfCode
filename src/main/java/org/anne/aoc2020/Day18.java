package org.anne.aoc2020;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day18 extends Day {

    public static void main(String[] args) {
        new Day18().run();
    }

    @Override
    public void execute() {
        setName("Operation Order");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static long part1(List<String> input) {
        return input.stream().mapToLong(s -> calculate(s, false)).sum();
    }

    public static long part2(List<String> input) {
        return input.stream().mapToLong(s -> calculate(s, true)).sum();
    }

    static long calculate(String operation, boolean isPart2) {

        while (operation.contains("(")) {
            int start = operation.lastIndexOf("(");
            int end = operation.indexOf(")", start);
            long result = calculate(operation.substring(start + 1, end), isPart2);
            operation = operation.substring(0, start) + result + operation.substring(end + 1);
        }

        long left = 0L;
        long right = 0L;
        boolean isAddition = true;
        List<String> op = new ArrayList<>(Arrays.asList(operation.split(" ")));
        if (isPart2) {
            while (op.contains("+")) {
                int plusIndex = op.indexOf("+");
                long result = Long.parseLong(op.get(plusIndex - 1)) + Long.parseLong(op.get(plusIndex + 1));
                op.set(plusIndex, String.valueOf(result));
                op.remove(plusIndex + 1);
                op.remove(plusIndex - 1);
            }
        }

        for (String c : op) {
            if (c.equals("+")) {
                isAddition = true;
            } else if (c.equals("*")) {
                isAddition = false;
            } else {
                if (left == 0) {
                    left = Long.parseLong(c);
                } else {
                    right = Long.parseLong(c);
                }
            }
            if (right != 0L) {
                if (isAddition) {
                    left += right;
                } else {
                    left *= right;
                }
                right = 0L;
            }
        }
        return left;
    }
}
