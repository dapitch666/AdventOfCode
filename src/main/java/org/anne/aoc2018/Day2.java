package org.anne.aoc2018;

import org.anne.common.Day;

import java.util.List;

public class Day2 extends Day {
    public static void main(String[] args) {
        new Day2().run();
    }

    @Override
    public void execute() {
        setName("Inventory Management System");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(List<String> input) {
        int two = 0;
        int three = 0;
        for (String s : input) {
            int[] counts = new int[26];
            for (char c : s.toCharArray()) {
                counts[c - 'a']++;
            }
            boolean hasTwo = false;
            boolean hasThree = false;
            for (int count : counts) {
                if (count == 2) {
                    hasTwo = true;
                } else if (count == 3) {
                    hasThree = true;
                }
            }
            if (hasTwo) {
                two++;
            }
            if (hasThree) {
                three++;
            }
        }
        return two * three;
    }

    public static String part2(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            for (int j = i + 1; j < input.size(); j++) {
                String s1 = input.get(i);
                String s2 = input.get(j);
                int diff = 0;
                int diffIndex = -1;
                for (int k = 0; k < s1.length(); k++) {
                    if (s1.charAt(k) != s2.charAt(k)) {
                        diff++;
                        diffIndex = k;
                    }
                }
                if (diff == 1) {
                    return s1.substring(0, diffIndex) + s1.substring(diffIndex + 1);
                }
            }
        }
        return "Not found";
    }
}
