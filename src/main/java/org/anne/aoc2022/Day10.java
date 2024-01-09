package org.anne.aoc2022;

import org.anne.common.Day;
import org.anne.common.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day10 extends Day {
    public static void main(String[] args) {
        Day day = new Day10();
        day.setName("Cathode-Ray Tube");
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    public static int part1(List<String> input) {
        int result = 0;
        List<Integer> cycles = getCycles(input);
        for (int i : Arrays.asList(20,60,100,140,180,220)) {
            result += cycles.get(i-1) * i;
        }
        return result;
    }

    public static String part2(List<String> input) {
        int[][] screen = new int[6][40];
        int position = 0;
        for (int i : getCycles(input)) {
            int row = position / 40;
            int col = position - 40 * row;
            if (i == col - 1 || i == col || i == col + 1) {
                screen[row][col] = 1;
            }
            position++;
        }
        return Utils.printAscii(screen);
    }

    public static List<Integer> getCycles(List<String> input) {
        int X = 1;
        List<Integer> cycles = new ArrayList<>();
        for (String line : input) {
            String[] params = line.split(" ");
            if (params[0].equals("noop")) {
                cycles.add(X);
            } else {
                cycles.add(X);
                cycles.add(X);
                X += Integer.parseInt(params[1]);
            }
        }
        cycles.add(X);
        return cycles;
    }
}
