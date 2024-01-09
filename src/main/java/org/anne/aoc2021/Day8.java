package org.anne.aoc2021;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day8 extends Day {

    public static void main(String[] args) {
        Day day = new Day8();
        day.setName("Seven Segment Search");
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    public static long part1(List<String> input) {
        List<Integer> integers = Arrays.asList(2, 3, 4, 7);
        int counter = 0;
        for (String line : input) {
            String[] split = line.split(" \\| ");
            counter += (int) Arrays.stream(split[1].split(" "))
                             .mapToInt(String::length)
                             .filter(integers::contains)
                             .count();
        }
        return counter;
    }

    public static int part2(List<String> input) {
        int sum = 0;
        for (String line : input) {
            String[] split = line.split(" \\| ");
            String[] digits = new String[10];
            List<String> fives = new ArrayList<>();
            List<String> sixes = new ArrayList<>();
            for (String word : split[0].split(" ")) {
                switch (word.length()) {
                    /* 1, 7, 4 and 8 have a distinct number of bars, so they can be assigned right away */
                    case 2 -> digits[1] = word;
                    case 3 -> digits[7] = word;
                    case 4 -> digits[4] = word;
                    case 7 -> digits[8] = word;
                    case 5 -> fives.add(word); // group of digits that have 5 bars: 2, 3 and 5
                    case 6 -> sixes.add(word); // group of digits that have 6 bars: 0, 6 and 9
                }
            }
            for (String word : fives) {
                if (contains(word, digits[1])) { // 1 fits in 3, but not in 2 and 5
                    digits[3] = word;
                }
            }
            fives.remove(digits[3]);

            for (String word : sixes) {
                if (contains(word, digits[3])) { // 3 fits in 9, but not in 0 and 6
                    digits[9] = word;
                }
            }
            sixes.remove(digits[9]);

            for (String word : fives) {
                if (contains(digits[9], word)) { // 5 fits in 9
                    digits[5] = word;
                } else {    // So the last digit is 2
                    digits[2] = word;
                }
            }
            for (String word : sixes) {
                if (contains(word, digits[5])) { // 5 fits in 6
                    digits[6] = word;
                } else {    // So the last one is 0
                    digits[0] = word;
                }
            }
            sum += calculateOutput(split[1], digits);
        }
        return sum;
    }

    private static int calculateOutput(String s, String[] digits) {
        StringBuilder sb = new StringBuilder();
        for (String word : s.split(" ")) {
            sb.append(decodeWord(word, digits));
        }
        return Integer.parseInt(sb.toString());
    }

    public static boolean contains(String word, String letters) {
        for (char c : letters.toCharArray()) {
            if (word.indexOf(c) == -1) {
                return false;
            }
        }
        return true;
    }

    public static boolean areEquals(String a, String b) {
        char[] ca = a.toCharArray();
        char[] cb = b.toCharArray();
        Arrays.sort(ca);
        Arrays.sort(cb);
        return String.valueOf(ca).equals(String.valueOf(cb));
    }

    public static int decodeWord(String s, String[] digits) {
        for (int i = 0; i < digits.length; i++) {
            if (areEquals(s, digits[i])) {
                return i;
            }
        }
        return 0;
    }

}
