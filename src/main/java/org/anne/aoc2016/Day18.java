package org.anne.aoc2016;

import org.anne.common.Day;

import java.util.Arrays;

public class Day18 extends Day {
    public static void main(String[] args) {
        new Day18().run();
    }

    @Override
    public void execute() {
        setName("Like a Rogue");
        String input = readFileOneLine();
        setPart1(part1(input, 40));
        setPart2(part2(input, 400000));
    }

    public static int part1(String input, int rows) {
        return countSafe(input, rows);
    }

    public static int part2(String input, int rows) {
        return countSafe(input, rows);
    }

    private static int countSafe(String input, int rows) {
        int[] row = input.chars().map(c -> c == '^' ? 0 : 1).toArray();
        int count = Arrays.stream(row).sum();
        for (int y = 1; y < rows; y++) {
            int[] newRow = new int[row.length];
            for (int x = 0; x < row.length; x++) {
                int left = x > 0 ? row[x - 1] : 1;
                int right = x < row.length - 1 ? row[x + 1] : 1;
                newRow[x] = left == right ? 1 : 0;
            }
            count += Arrays.stream(newRow).sum();
            row = newRow;
        }
        return count;
    }
}
