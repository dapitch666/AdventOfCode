package org.anne.aoc2015;

import org.anne.common.Day;

import java.util.List;

public class Day5 extends Day {
    public static void main(String[] args) {
        new Day5().run();
    }

    @Override
    public void execute() {
        setName("Doesn't He Have Intern-Elves For This?");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(List<String> input) {
        return (int) input.stream().filter(Day5::isNicePart1).count();
    }

    public static int part2(List<String> input) {
        return (int) input.stream().filter(Day5::isNicePart2).count();
    }

    private static boolean isNicePart1(String string) {
        return has3vowels(string) && hasDoubleLetter(string) && hasNoDisallowedSubstrings(string);
    }

    private static boolean isNicePart2(String string) {
        return aPairAppearsTwice(string) && hasRepeatingLetter(string);
    }

    private static boolean hasRepeatingLetter(String string) {
        for (int i = 1; i < string.length() - 1; i++) {
            if (string.charAt(i - 1) == string.charAt(i + 1)) {
                return true;
            }
        }
        return false;
    }

    private static boolean aPairAppearsTwice(String string) {
        for (int i = 1; i < string.length(); i++) {
            if (string.indexOf(string.substring(i - 1, i + 1), i + 1) != -1) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasNoDisallowedSubstrings(String string) {
        return !string.contains("ab") && !string.contains("cd") && !string.contains("pq") && !string.contains("xy");
    }

    private static boolean hasDoubleLetter(String string) {
        for (int i = 1; i < string.length(); i++) {
            if (string.charAt(i - 1) == string.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    private static boolean has3vowels(String string) {
        int vowelCount = 0;
        for (char c : string.toCharArray()) {
            if ("aeiou".indexOf(c) != -1 && ++vowelCount >= 3) {
                return true;
            }
        }
        return false;
    }
}
