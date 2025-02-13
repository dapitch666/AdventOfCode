package org.anne.aoc2015;

import org.anne.common.Day;
import org.anne.common.Utils;

import java.util.Arrays;
import java.util.List;

public class Day6 extends Day {
    public static void main(String[] args) {
        new Day6().run();
    }

    @Override
    public void execute() {
        setName("Probably a Fire Hazard");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(List<String> input) {
        int[][] grid = new int[1000][1000];

        for (String line : input) {
            int[] ints = Utils.inputToIntStream(line).toArray();
            boolean toggle = line.startsWith("toggle");
            boolean turnOn = line.startsWith("turn on");

            for (int y = ints[0]; y <= ints[2]; y++) {
                for (int x = ints[1]; x <= ints[3]; x++) {
                    if (toggle) {
                        grid[y][x] = grid[y][x] == 0 ? 1 : 0;
                    } else {
                        grid[y][x] = turnOn ? 1 : 0;
                    }
                }
            }
        }

        return Arrays.stream(grid).flatMapToInt(Arrays::stream).sum();
    }

    public static int part2(List<String> input) {
        int[][] grid = new int[1000][1000];

        for (String line : input) {
            int[] ints = Utils.inputToIntStream(line).toArray();
            boolean toggle = line.startsWith("toggle");
            boolean turnOn = line.startsWith("turn on");

            for (int y = ints[0]; y <= ints[2]; y++) {
                for (int x = ints[1]; x <= ints[3]; x++) {
                    if (toggle) {
                        grid[y][x] += 2;
                    } else {
                        grid[y][x] = turnOn ? grid[y][x] + 1 : Math.max(grid[y][x] - 1, 0);
                    }
                }
            }
        }

        return Arrays.stream(grid).flatMapToInt(Arrays::stream).sum();
    }
}