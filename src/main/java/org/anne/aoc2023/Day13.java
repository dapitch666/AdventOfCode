package org.anne.aoc2023;

import org.anne.common.Day;

import java.util.*;

import static org.anne.common.Utils.transpose;

public class Day13 extends Day {
    public static void main(String[] args) {
        Day day = new Day13();
        day.setName("Point of Incidence");
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }


    public static int part1(List<String> input) {
        var patterns = readInput(input);
        return patterns.stream().mapToInt(p -> getLineOfReflexion(p, true, 0)).sum() +
                100 * patterns.stream().mapToInt(p -> getLineOfReflexion(p, false, 0)).sum();
    }

        public static int part2(List<String> input) {
        var patterns = readInput(input);
        return patterns.stream().mapToInt(p -> getLineOfReflexion(p, true, 1)).sum() +
                100 * patterns.stream().mapToInt(p -> getLineOfReflexion(p, false, 1)).sum();
    }

    static int getLineOfReflexion(List<String> pattern, boolean vertical, int smudge) {
        if (vertical) {
            pattern = transpose(pattern);
        }
        for (var i = 1; i < pattern.size(); i++) {
            int diff = 0;
            for (var j = 0; j < Math.min(i, pattern.size() - i); j++) {
                diff += difference(pattern.get(i - j - 1), pattern.get(i + j));
            }
            if (diff == smudge) {
                return i;
            }
        }
        return 0;  // no reflexion
    }
    
    private static int difference(String s1, String s2) {
        if (s1.equals(s2)) {
            return 0;
        } else {
            int diff = 0;
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    diff++;
                }
            }
            return diff;
        }
    }
    
    private static ArrayList<List<String>> readInput(List<String> input) {
        var patterns = new ArrayList<List<String>>();
        var pattern = new ArrayList<String>();
        for (var line : input) {
            if (line.isEmpty()) {
                patterns.add(pattern);
                pattern = new ArrayList<>();
                continue;
            }
            pattern.add(line);
        }
        patterns.add(pattern);
        return patterns;
    }
}
