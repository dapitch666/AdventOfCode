package org.anne.aoc2016;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day21 extends Day {
    public static void main(String[] args) {
        new Day21().run();
    }

    @Override
    public void execute() {
        setName("Scrambled Letters and Hash");
        List<String> input = readFile();
        setPart1(part1(input, "abcdefgh"));
        setPart2(part2(input, "fbgdceah"));
    }

    public static String part1(List<String> input, String password) {
        return scramble(password, input, false);
    }

    public static String part2(List<String> input, String password) {
        return scramble(password, input, true);
    }

    private static String scramble(String password, List<String> input, boolean undo) {
        List<Character> pwd = password.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<String> commands = undo ? new ArrayList<>(input).reversed() : input;

        for (String line : commands) {
            String[] parts = line.split(" ");
            switch (parts[0] + " " + parts[1]) {
                case "swap position" -> Collections.swap(pwd, Integer.parseInt(parts[2]), Integer.parseInt(parts[5]));
                case "swap letter" -> Collections.swap(pwd, pwd.indexOf(parts[2].charAt(0)), pwd.indexOf(parts[5].charAt(0)));
                case "rotate left" -> Collections.rotate(pwd, Integer.parseInt(parts[2]) * (undo ? 1 : -1));
                case "rotate right" -> Collections.rotate(pwd, Integer.parseInt(parts[2]) * (undo ? -1 : 1));
                case "rotate based" -> rotate(pwd, parts[6].charAt(0), undo);
                case "reverse positions" -> Collections.reverse(pwd.subList(Integer.parseInt(parts[2]), Integer.parseInt(parts[4]) + 1));
                case "move position" -> pwd.add(Integer.parseInt(undo ? parts[2] : parts[5]), pwd.remove(Integer.parseInt(undo ? parts[5] : parts[2])));
            }
        }
        return pwd.stream().map(String::valueOf).collect(Collectors.joining());
    }

    private static void rotate(List<Character> pwd, char c, boolean undo) {
        int x = pwd.indexOf(c);
        int step = undo ? (x / 2 + (x % 2 == 1 || x == 0 ? 1 : 5)) * -1 : x + 1 + (x >= 4 ? 1 : 0);
        Collections.rotate(pwd, step);
    }
}