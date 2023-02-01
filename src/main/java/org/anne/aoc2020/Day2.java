package org.anne.aoc2020;

import org.anne.common.Day;

import java.util.List;

public class Day2 extends Day {

    public static void main(String[] args) {
        Day day = new Day2();
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    static long part1(List<String> input) {
        return input.stream().filter(i -> parseInput(i).isValidPart1()).count();
    }

    static long part2(List<String> input) {
        return input.stream().filter(i -> parseInput(i).isValidPart2()).count();
    }

    record ParsedLine (String pwd, char letter, int min, int max) {
        boolean isValidPart1() {
            long occurrence = pwd.chars().filter(c -> c == letter).count();
            return occurrence >= min && occurrence <= max;
        }
        boolean isValidPart2() {
            return (pwd.charAt(min-1) == letter) ^ (pwd.charAt(max-1) == letter);
        }
    }

    private static ParsedLine parseInput(String input) {
        String[] x = input.split(":");
        String[] y = x[0].split(" ");
        String[] z = y[0].split("-");

        String pwd = x[1].trim();
        char letter = y[1].charAt(0);
        int min = Integer.parseInt(z[0]);
        int max = Integer.parseInt(z[1]);

        return new ParsedLine(pwd, letter, min, max);
    }

}