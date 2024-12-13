package org.anne.aoc2024;

import org.anne.common.Day;
import org.anne.common.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day13 extends Day {
    public static void main(String[] args) {
        Day day = new Day13();
        day.setName("Claw Contraption");
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    public static long part1(List<String> input) {
        List<Machine> machines = getMachines(input, 0);
        return machines.stream().mapToLong(Machine::minTokens).sum();
    }

    public static long part2(List<String> input) {
        List<Machine> machines = getMachines(input, 10000000000000L);
        return machines.stream().mapToLong(Machine::minTokens).sum();
    }

    private static List<Machine> getMachines(List<String> input, long offset) {
        input.removeIf(String::isEmpty);
        List<Machine> machines = new ArrayList<>();
        Pattern pattern = Pattern.compile("[XY][+=](\\d{1,5})");
        // Create a list of all the machines
        for (int i = 0; i < input.size(); i += 3) {
            Matcher matcher = pattern.matcher(input.get(i) + input.get(i + 1) + input.get(i + 2));
            List<Integer> parts = new ArrayList<>();
            while (matcher.find()) {
                parts.add(Integer.parseInt(matcher.group(1)));
            }
            machines.add(new Machine(parts.get(0), parts.get(1), parts.get(2), parts.get(3),
                    parts.get(4) + offset, parts.get(5) + offset));
        }
        return machines;
    }

    record Machine(long aX, long aY, long bX, long bY, long pX, long pY) {
        long minTokens() {
            long numerator = pX * aY - pY * aX;
            long b = numerator / (bX * aY - bY * aX);
            long remX = pX - b * bX;
            long l = aX == 0 ? pY : remX;
            long r = aX == 0 ? aY : aX;
            long a = l / r;
            return (a * aY + b * bY == pY && l % r == 0) ? 3 * a + b : 0;
        }
    }
}
