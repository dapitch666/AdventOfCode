package org.anne.aoc2019;


import org.anne.common.Day;
import org.anne.common.Utils;

public class Day8  extends Day {

    public static void main(String[] args) {
        Day day = new Day8();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Space Image Format");
        String input = this.readFileOneLine();
        this.setPart1(part1(input, 25, 6));
        this.setPart2(part2(input, 25, 6));
        this.printParts();
    }

    static long part1(String input, int width, int height) {
        String pattern = "(?<=\\G" + ".".repeat(width * height) + ")";
        String[] layers = input.split(pattern);
        long minCount = (long) width * height;
        long calc = 0;
        for (String s : layers) {
            long count = s.chars().filter(ch -> ch == '0').count();
            if (count < minCount) {
                minCount = count;
                calc = s.chars().filter(ch -> ch == '1').count() * s.chars().filter(ch -> ch == '2').count();
            }
        }
        return calc;
    }

    static String part2(String input, int width, int height) {
        String pattern = "(?<=\\G" + ".".repeat(width * height) + ")";
        String[] layers = input.split(pattern);
        int[][] image = new int[height][width];
        for (int i = 0; i < width * height; i++) {
            int row = i / width;
            int col = i - width * row;
            for (String layer : layers) {
                if (layer.charAt(i) == '0') {
                    image[row][col] = 0;
                    break;
                } else if (layer.charAt(i) == '1') {
                    image[row][col] = 1;
                    break;
                }
            }
        }
        return Utils.printAscii(image);
    }
}
