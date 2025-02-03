package org.anne.aoc2017;

import org.anne.common.Day;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Day7 extends Day {
    public static void main(String[] args) {
        new Day7().run();
    }

    @Override
    public void execute() {
        setName("Recursive Circus");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static String part1(List<String> input) {
        List<String> candidates = new ArrayList<>();
        Set<String> children = new HashSet<>();
        input.forEach(line -> {
            if (line.contains("->")) {
                candidates.add(line.split(" ")[0]);
                children.addAll(List.of(line.split("-> ")[1].split(", ")));
            }
        });
        return candidates.stream().filter(c -> !children.contains(c)).findFirst().orElseThrow();
    }

    public static int part2(List<String> input) {
        List<Program> programs = input.stream().map(line -> {
            String[] parts = line.split(" ");
            String name = parts[0];
            int weight = Integer.parseInt(parts[1].substring(1, parts[1].length() - 1));
            String[] children = line.contains("->") ? line.split("-> ")[1].split(", ") : new String[0];
            return new Program(name, new AtomicInteger(weight), List.of(children), new ArrayList<>());
        }).toList();
        programs.forEach(p -> p.setChildren(programs));

        for (Program program : programs.stream().filter(p -> !p.children.isEmpty() && p.isUnbalanced()).toList()) {
            Map<Program, Integer> weights = program.children.stream()
                    .collect(Collectors.toMap(p -> p, Program::totalWeight));
            int normalWeight = weights.values().stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                    .entrySet().stream().filter(e -> e.getValue() > 1).map(Map.Entry::getKey).findFirst().orElse(0);
            Program programToFix = weights.entrySet().stream()
                    .filter(e -> e.getValue() != normalWeight)
                    .map(Map.Entry::getKey)
                    .findFirst().orElseThrow();
            int oldWeight = programToFix.weight.get();
            int fixedWeight = oldWeight + (normalWeight - weights.get(programToFix));
            programToFix.weight.set(fixedWeight);
            if (programs.stream().noneMatch(Program::isUnbalanced)) {
                return fixedWeight;
            }
            programToFix.weight.set(oldWeight);
        }
        return -1;
    }

    record Program(String name, AtomicInteger weight, List<String> childrenNames, List<Program> children) {
        void setChildren(List<Program> programs) {
            children.addAll(programs.stream().filter(p -> childrenNames.contains(p.name)).toList());
        }

        int totalWeight() {
            return weight.get() + children.stream().mapToInt(Program::totalWeight).sum();
        }

        boolean isUnbalanced() {
            return children.stream().mapToInt(Program::totalWeight).distinct().count() > 1;
        }
    }
}
