package org.anne.aoc2019;


import org.anne.common.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day7 extends Day {

    public static void main(String[] args) {
        Day day = new Day7();
        String input = day.readFileOneLine();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    static long part1(String input) {
        long maxOutput = 0;
        List<int[]> amplifiers = getAmpliOrders(0, 4);
        for(int[] ampliOrder : amplifiers) {
            long output = 0;
            for (int i : ampliOrder) {
                Computer computer = new Computer(input);
                computer.writeInput(new long[]{i, output});
                computer.compute();
                output = computer.getOutput();
            }
            if (output > maxOutput) {
                maxOutput = output;
            }
        }
        return maxOutput;
    }

    static long part2(String input) {
        int maxOutput = 0;
        List<int[]> amplifiers = getAmpliOrders(5, 9);
        for (int[] ampliOrder : amplifiers) {
            Computer[] computers = new Computer[]{
                    new Computer(input),
                    new Computer(input),
                    new Computer(input),
                    new Computer(input),
                    new Computer(input)
            };
            int output = 0;
            for (int i = 0; i < computers.length; i++) {
                computers[i].writeInput(new long[]{ampliOrder[i]});
            }
            while(computers[4].isStillRunning()) {
                for (Computer computer : computers) {
                    computer.compute(output);
                    output = (int) computer.getOutput();
                }
            }
            if (output > maxOutput) {
                maxOutput = output;
            }
        }
        return maxOutput;
    }

    private static List<int[]> getAmpliOrders(int min, int max) {
        List<int[]> ampliOrders = new ArrayList<>();
        for (int a = min; a <= max; a++) {
            for (int b = min; b <= max; b++) {
                for (int c = min; c <= max; c++) {
                    for (int d = min; d <= max; d++) {
                        for (int e = min; e <= max; e++) {
                            int[] amplifiers = {a, b, c, d, e};
                            if (Arrays.stream(amplifiers).distinct().count() == 5) {
                                ampliOrders.add(amplifiers);
                            }
                        }
                    }
                }
            }
        }
        return ampliOrders;
    }
}
