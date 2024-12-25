package org.anne.aoc2024;

import org.anne.common.Day;
import org.anne.common.GridHelper;

import java.util.ArrayList;
import java.util.List;

public class Day25 extends Day {
    public static void main(String[] args) {
        new Day25().run();
    }

    @Override
    public void execute() {
        setName("Code Chronicle");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2());
        printParts();
    }

    public static int part1(List<String> input) {
        int result = 0;
        List<char[][]> locks = new ArrayList<>();
        List<char[][]> keys = new ArrayList<>();
        for (int i = 0; i < input.size(); i += 8) {
            // ignoring first and last lines of each grid as they are all dots or hashes
            (input.get(i).startsWith("#") ? locks : keys).add(GridHelper.getCharGrid(input.subList(i + 1, i + 6)));
        }

        for (char[][] lock : locks) {
            for (char[][] key : keys) {
                if (fits(lock, key)) {
                    result++;
                }
            }
        }
        return result;
    }

    public static String part2() {
        return "Happy Tenth Anniversary, AdventOfCode!";
    }

    private static boolean fits(char[][] lock, char[][] key) {
        for (int y = 0; y < key.length; y++) {
            for (int x = 0; x < key[y].length; x++) {
                if (lock[y][x] == '#' && key[y][x] == '#') {
                    return false;
                }
            }
        }
        return true;
    }
}
