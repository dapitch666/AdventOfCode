package org.anne.aoc2017;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.anne.common.Utils.inputToIntStream;

public class Day12 extends Day {
    public static void main(String[] args) {
        new Day12().run();
    }

    @Override
    public void execute() {
        setName("Digital Plumber");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(List<String> input) {
        List<Set<Integer>> groups = getGroups(input);
        return groups.stream().filter(group -> group.contains(0)).findFirst().orElseThrow().size();
    }

    public static int part2(List<String> input) {
        List<Set<Integer>> groups = getGroups(input);
        return groups.size();
    }

    private static List<Set<Integer>> getGroups(List<String> input) {
        List<Set<Integer>> groups = new ArrayList<>();
        for (String line : input) {
            Set<Integer> connected = inputToIntStream(line).boxed().collect(Collectors.toSet());
            Set<Integer> group = new HashSet<>();
            groups.removeIf(existingGroup -> {
                if (existingGroup.stream().anyMatch(connected::contains)) {
                    group.addAll(existingGroup);
                    return true;
                }
                return false;
            });
            group.addAll(connected);
            groups.add(group);
        }
        return groups;
    }
}
