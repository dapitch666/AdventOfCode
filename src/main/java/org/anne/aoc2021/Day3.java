package org.anne.aoc2021;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Day3 extends Day {

    public static void main(String[] args) {
        Day day = new Day3();
        day.setName("Binary Diagnostic");
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();

    }

    public static long part1(List<String> input) {
        List<String> transposedInput = transpose(input);
        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();
        for (String s : transposedInput) {
            if (mostFrequentIsOne(s)) {
                gamma.append("1");
                epsilon.append("0");
            } else {
                gamma.append("0");
                epsilon.append("1");
            }
        }
        return Long.parseLong(gamma.toString(), 2) * Long.parseLong(epsilon.toString(), 2);
    }

    public static long part2(List<String> input) {
        String oxygen = getRating(new LinkedList<>(input), "oxygen");
        String co2 = getRating(new LinkedList<>(input), "co2");
        return Long.parseLong(oxygen, 2) * Long.parseLong(co2, 2);
    }

    private static String getRating(LinkedList<String> input, String type) {
        for (int i = 0; i < input.getFirst().length(); i++) {
            int finalI = i;
            char c;
            if (type.equals("oxygen")) {
                c = mostFrequentIsOne(transpose(input).get(finalI)) ? '1' : '0';
            } else {
                c = mostFrequentIsOne(transpose(input).get(finalI)) ? '0' : '1';
            }
            input.removeIf(s -> s.charAt(finalI) != c);
            if (input.size() == 1) {
                return input.get(0);
            }
        }
        return "";
    }

    private static boolean mostFrequentIsOne(String s) {
        double halfStringLength = (double)s.length() / 2;
        return s.chars().filter(c -> c == '1').count() >= halfStringLength;
    }

    static List<String> transpose(List<String> table) {
        List<String> ret = new ArrayList<>();
        for (int i = 0; i < table.get(0).length(); i++) {
            StringBuilder col = new StringBuilder();
            for (String row : table) {
                col.append(row.charAt(i));
            }
            ret.add(col.toString());
        }
        return ret;
    }
}
