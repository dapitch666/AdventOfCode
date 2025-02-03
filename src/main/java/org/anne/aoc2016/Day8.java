package org.anne.aoc2016;

import org.anne.common.Day;
import org.anne.common.Utils;

import java.util.Arrays;
import java.util.List;

public class Day8 extends Day {
    public static void main(String[] args) {
        new Day8().run();
    }

    @Override
    public void execute() {
        setName("Two-Factor Authentication");
        List<String> input = readFile();
        setPart1(part1(input, 50, 6));
        setPart2(part2(input, 50, 6));
        printParts();
    }

    public static int part1(List<String> input, int width, int height) {
        var screen = getScreen(input, width, height);
        return Arrays.stream(screen).flatMapToInt(Arrays::stream).sum();
    }

    public static String part2(List<String> input, int width, int height) {
        var screen = getScreen(input, width, height);
        // System.out.println(Utils.print2dIntArray(screen, " ", "#"));
        return Utils.ocr(screen, 5, 6);
    }

    private static int[][] getScreen(List<String> input, int width, int height) {
        int[][] screen = new int[height][width];
        for (String line : input) {
            var ints = Utils.inputToIntStream(line).toArray();
            if (line.startsWith("rect")) {
                for (int y = 0; y < ints[1]; y++) {
                    for (int x = 0; x < ints[0]; x++) {
                        screen[y][x] = 1;
                    }
                }
            }
            if (line.startsWith("rotate row")) {
                int[] newRow = new int[width];
                for (int x = 0; x < width; x++) {
                    newRow[(x + ints[1]) % width] = screen[ints[0]][x];
                }
                screen[ints[0]] = newRow;
            }
            if (line.startsWith("rotate column")) {
                int[] newColumn = new int[height];
                for (int y = 0; y < height; y++) {
                    newColumn[(y + ints[1]) % height] = screen[y][ints[0]];
                }
                for (int y = 0; y < height; y++) {
                    screen[y][ints[0]] = newColumn[y];
                }
            }
        }
        return screen;
    }
}
