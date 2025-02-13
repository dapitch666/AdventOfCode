package org.anne.aoc2015;

import org.anne.common.Day;

import java.util.List;

public class Day8 extends Day {
    public static void main(String[] args) {
        new Day8().run();
    }

    @Override
    public void execute() {
        setName("Matchsticks");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(List<String> input) {
        int stringSize = input.stream().mapToInt(String::length).sum();
        int memorySize = input.stream().mapToInt(Day8::memorySize).sum();
        return stringSize - memorySize;
    }

    public static int part2(List<String> input) {
        int stringSize = input.stream().mapToInt(String::length).sum();
        int encodedSize = input.stream().mapToInt(Day8::encodedSize).sum();
        return encodedSize - stringSize;
    }

    private static int memorySize(String s) {
        return s.replaceAll("\\\\{2}", "1")
                .replaceAll("\\\\\"", "1")
                .replaceAll("\\\\x..", "1")
                .replaceAll("\"", "")
                .length();
    }

    private static int encodedSize(String s) {
        return s.replaceAll("\\\\{2}", "1234")
                .replaceAll("\\\\\"", "1234")
                .replaceAll("\\\\x..", "12345")
                .replaceAll("\"", "12")
                .length() + 2;
    }
}