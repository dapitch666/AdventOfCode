package org.anne.aoc2017;

import org.anne.common.Day;

import java.util.Arrays;
import java.util.List;

public class Day16 extends Day {
    public static void main(String[] args) {
        new Day16().run();
    }

    @Override
    public void execute() {
        setName("Permutation Promenade");
        String input = readFileOneLine();
        setPart1(part1(input, 16));
        setPart2(part2(input));
        printParts();
    }

    public static String part1(String input, int size) {
        char[] programs = "abcdefghijklmnop".substring(0, size).toCharArray();
        List<String> moves = List.of(input.split(","));
        dance(moves, programs);
        return new String(programs);
    }

    public static String part2(String input) {
        List<String> moves = List.of(input.split(","));
        char[] programs = "abcdefghijklmnop".toCharArray();
        int remaining = 1_000_000_000 % cycleLength(moves);
        while (remaining-- > 0) {
            dance(moves, programs);
        }
        return new String(programs);
    }

    private static int cycleLength(List<String> moves) {
        char[] programs = "abcdefghijklmnop".toCharArray();
        char[] original = programs.clone();
        int cycleLength = 0;
        do {
            cycleLength++;
            dance(moves, programs);
        } while (!Arrays.equals(programs, original));
        return cycleLength;
    }

    private static void dance(List<String> moves, char[] programs) {
        for (String move : moves) {
            String[] args = move.substring(1).split("/");
            switch (move.charAt(0)) {
                case 's' -> spin(programs, Integer.parseInt(args[0]));
                case 'x' -> swap(programs, Integer.parseInt(args[0]), Integer.parseInt(args[1]));
                case 'p' -> swap(programs, args[0].charAt(0), args[1].charAt(0));
            }
        }
    }

    private static void spin(char[] programs, int spin) {
        int size = programs.length;
        char[] temp = programs.clone();
        for (int i = 0; i < size; i++) {
            programs[(i + spin) % size] = temp[i];
        }
    }

    private static void swap(char[] programs, int a, int b) {
        char temp = programs[a];
        programs[a] = programs[b];
        programs[b] = temp;
    }

    private static void swap(char[] programs, char a, char b) {
        swap(programs, findIndex(programs, a), findIndex(programs, b));
    }

    static int findIndex(char[] programs, char c) {
        for (int i = 0; i < programs.length; i++) {
            if (programs[i] == c) {
                return i;
            }
        }
        return -1;
    }
}
