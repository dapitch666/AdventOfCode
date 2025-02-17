package org.anne.aoc2015;

import org.anne.common.Day;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day16 extends Day {
    public static void main(String[] args) {
        new Day16().run();
    }

    @Override
    public void execute() {
        setName("Aunt Sue");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    private static final Pattern PATTERN = Pattern.compile("Sue (\\d+): (\\w+: \\d+), (\\w+: \\d+), (\\w+: \\d+)");
    private static final List<String> MFCSAM = List.of("children: 3", "cats: 7", "samoyeds: 2", "pomeranians: 3",
            "akitas: 0", "vizslas: 0", "goldfish: 5", "trees: 3", "cars: 2", "perfumes: 1");

    public static String part1(List<String> input) {
        return input.stream()
                .map(PATTERN::matcher)
                .filter(Matcher::find)
                .filter(m -> MFCSAM.contains(m.group(2)) && MFCSAM.contains(m.group(3)) && MFCSAM.contains(m.group(4)))
                .map(m -> m.group(1))
                .findFirst()
                .orElseThrow();
    }

    public static String part2(List<String> input) {
        Map<String, Integer> mfcsam = MFCSAM.stream()
                .map(s -> s.split(": "))
                .collect(HashMap::new, (m, p) -> m.put(p[0], Integer.parseInt(p[1])), Map::putAll);

        return input.stream()
                .map(PATTERN::matcher)
                .filter(Matcher::find)
                .filter(m -> matchesExpected(m.group(2), mfcsam) && matchesExpected(m.group(3), mfcsam) && matchesExpected(m.group(4), mfcsam))
                .map(m -> m.group(1))
                .findFirst()
                .orElseThrow();
    }

    private static boolean matchesExpected(String s, Map<String, Integer> map) {
        var parts = s.split(": ");
        int actual = Integer.parseInt(parts[1]);
        int expected = map.get(parts[0]);

        return switch (parts[0]) {
            case "cats", "trees" -> actual > expected;
            case "pomeranians", "goldfish" -> actual < expected;
            default -> actual == expected;
        };
    }
}
