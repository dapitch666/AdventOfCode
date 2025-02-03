package org.anne.aoc2016;

import org.anne.common.Day;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 extends Day {
    public static void main(String[] args) {
        new Day7().run();
    }

    @Override
    public void execute() {
        setName("Internet Protocol Version 7");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    private static final Pattern PATTERN = Pattern.compile("(\\[.*?])|([^\\[\\]]+)");

    public static int part1(List<String> input) {
        return (int) input.stream()
                .filter(Day7::supportsTLS)
                .count();
    }

    public static int part2(List<String> input) {
        return (int) input.stream()
                .filter(Day7::supportsSSL)
                .count();
    }

    private static boolean supportsTLS(String ipAddress) {
        Matcher matcher = PATTERN.matcher(ipAddress);
        boolean result = false;

        while (matcher.find()) {
            String match = matcher.group();
            if (match.startsWith("[") && abba(match.substring(1, match.length() - 1))) {
                return false;
            } else if (!match.startsWith("[") && abba(match)) {
                result = true;
            }
        }
        return result;
    }

    private static boolean abba(String s) {
        for (int i = 0; i < s.length() - 3; i++) {
            if (s.charAt(i) == s.charAt(i + 3) && s.charAt(i + 1) == s.charAt(i + 2) && s.charAt(i) != s.charAt(i + 1)) {
                return true;
            }
        }
        return false;
    }

    private static boolean supportsSSL(String ipAddress) {
        Matcher matcher = PATTERN.matcher(ipAddress);
        Set<String> aba = new HashSet<>();
        List<String> hypernetSequences = new ArrayList<>();

        while (matcher.find()) {
            String match = matcher.group();
            if (match.startsWith("[")) {
                hypernetSequences.add(match.substring(1, match.length() - 1));
            } else {
                aba.addAll(findABA(match));
            }
        }

        for (String sequence : hypernetSequences) {
            for (int i = 0; i < sequence.length() - 2; i++) {
                if (aba.contains(sequence.substring(i, i + 3))) {
                    return true;
                }
            }
        }
        return false;
    }

    private static Set<String> findABA(String s) {
        Set<String> aba = new HashSet<>();
        for (int i = 0; i < s.length() - 2; i++) {
            char firstChar = s.charAt(i);
            char secondChar = s.charAt(i + 1);
            if (firstChar == s.charAt(i + 2) && firstChar != secondChar) {
                aba.add("" + secondChar + firstChar + secondChar);
            }
        }
        return aba;
    }
}
