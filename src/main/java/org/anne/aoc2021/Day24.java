package org.anne.aoc2021;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Day24 extends Day {

    public static void main(String[] args) {
        Day day = new Day24();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Arithmetic Logic Unit");
        List<String> input = this.readFile();
        this.setPart1(part1(input));
        this.setPart2(part2(input));
        this.printParts();
    }

    public static long part1(List<String>  input) {
        return calcModelNumber(input, true);
    }

    public static long part2(List<String>  input) {
        return calcModelNumber(input, false);
    }

    private static long calcModelNumber(List<String> input, boolean isMax) {
        List<String[]> instructions = input.stream().map(s -> s.split(" ")).toList();
        List<int[]> instructionsLists = new ArrayList<>();
        for (int i = 0; i < input.size(); i = i + 18) {
            List<String[]> instr = instructions.subList(i, i + 18);
            instructionsLists.add(new int[] {Integer.parseInt(instr.get(5)[2]), Integer.parseInt(instr.get(15)[2])});
        }
        Stack<Integer> indexStack = new Stack<>();
        indexStack.push(0);
        int[] modelNumber = new int[14];
        int unit = isMax ? 9 : 1;
        int i = 1;
        while (!indexStack.isEmpty()) {
            if (instructionsLists.get(i)[0] >= 0) {
                indexStack.push(i);
            } else {
                int j = indexStack.pop();
                int calc = instructionsLists.get(i)[0] + instructionsLists.get(j)[1];
                if ((isMax && calc >= 0) || (!isMax && calc < 0)) {
                    modelNumber[i] = unit;
                    modelNumber[j] = unit - calc;
                } else {
                    modelNumber[i] = unit + calc;
                    modelNumber[j] = unit;
                }
            }
            i++;
        }
        return Long.parseLong(Arrays.toString(modelNumber).replaceAll("[,\\[\\] ]", ""));
    }
}
