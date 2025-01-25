package org.anne.aoc2017;

import org.anne.common.Day;

import java.util.Arrays;

import static org.anne.aoc2017.KnotHash.knotHash;
import static org.anne.aoc2017.KnotHash.knotHashBinary;

public class Day14 extends Day {
    public static void main(String[] args) {
        new Day14().run();
    }

    @Override
    public void execute() {
        setName("Disk Defragmentation");
        String input = readFileOneLine();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(String input) {
        String[] grid = new String[128];
        for (int i = 0; i < 128; i++) {
            grid[i] = knotHashBinary(input + "-" + i);
        }
        return Arrays.stream(grid).mapToInt(s -> (int) s.chars().filter(c -> c == '1').count()).sum();
    }

    public static int part2(String input) {
        Boolean[][] grid = new Boolean[128][128];
        for (int y = 0; y < 128; y++) {
            String hash = knotHashBinary(input + "-" + y);
            for (int x = 0; x < 128; x++) {
                    grid[y][x] = hash.charAt(x) == '1';
            }
        }
        int regions = 0;
        for (int y = 0; y < 128; y++) {
            for (int x = 0; x < 128; x++) {
                if (grid[y][x]) {
                    regions++;
                    markRegion(grid, x, y);
                }
            }
        }
        return regions;
    }

    private static void markRegion(Boolean[][] grid, int x, int y) {
        if (x < 0 || x >= 128 || y < 0 || y >= 128 || !grid[y][x]) {
            return;
        }
        grid[y][x] = false;
        markRegion(grid, x - 1, y);
        markRegion(grid, x + 1, y);
        markRegion(grid, x, y - 1);
        markRegion(grid, x, y + 1);
    }
}
