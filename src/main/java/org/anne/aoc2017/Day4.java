package org.anne.aoc2017;

import org.anne.common.Day;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day4 extends Day {
    public static void main(String[] args) {
        new Day4().run();
    }

    @Override
    public void execute() {
        setName("High-Entropy Passphrases");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(List<String> input) {
        return (int) input.stream().filter(Day4::noDuplicate).count();
    }

    public static int part2(List<String> input) {
        return (int) input.stream().filter(Day4::noAnagram).count();
    }

    private static boolean noDuplicate(String passphrase) {
        Set<String> words = new HashSet<>();
        for (String word : passphrase.split(" ")) {
            if (!words.add(word)) {
                return false;
            }
        }
        return true;
    }

    private static boolean noAnagram(String passphrase) {
        Set<String> words = new HashSet<>();
        for (String word : passphrase.split(" ")) {
            String chars = word.chars().sorted()
                    .mapToObj(i -> (char) i)
                    .map(String::valueOf)
                    .collect(Collectors.joining());
            if (!words.add(chars)) {
                return false;
            }
        }
        return true;
    }
}
