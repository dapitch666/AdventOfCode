package org.anne.aoc2022;

import org.anne.common.Day;

import java.util.List;

public class Day25 extends Day {
    public static void main(String[] args) {
        Day day = new Day25();
        day.setName("Full of Hot Air");
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2());
        day.printParts();
    }

    public static String part1(List<String> input) {
        long total = 0L;
        for (String number : input) {
            total += snafu2decimal(number);
        }
        return decimal2snafu(total);
    }

    @SuppressWarnings("SameReturnValue")
    public static String part2() {
        return "Merry Christmas!";
    }

    public static long snafu2decimal(String s) {
        long number = 0;
        int i = 0, l = s.length();
        for (char c : s.toCharArray()) {
            var pow = l - 1 - i;
            int n = switch (c) {
                case '=' -> -2;
                case '-' -> -1;
                default -> Integer.parseInt(String.valueOf(c));
            };
            number += (long) (n * Math.pow(5, pow));
            i++;
        }
        return number;
    }

    public static String decimal2snafu(long l) {
        if (l == 0) return "";
        else {
            int i = (int) (l % 5);
            return switch (i) {
                case 3 -> decimal2snafu((l + 2) / 5) + "=";
                case 4 -> decimal2snafu((l + 1) / 5) + "-";
                default -> decimal2snafu(l / 5) + i;
            };
        }
    }
}
