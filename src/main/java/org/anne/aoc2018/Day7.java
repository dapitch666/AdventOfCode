package org.anne.aoc2018;

import org.anne.common.Day;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Day7 extends Day {
    public static void main(String[] args) {
        new Day7().run();
    }

    @Override
    public void execute() {
        setName("The Sum of Its Parts");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input, 5));
        printParts();
    }

    public static String part1(List<String> input) {
        List<Step> steps = getSteps(input);
        StringBuilder result = new StringBuilder();
        while (!steps.isEmpty()) {
            Step next = steps.stream()
                    .filter(s -> s.before.isEmpty())
                    .min(Comparator.comparing(Step::name))
                    .orElseThrow();
            result.append(next.name);
            steps.remove(next);
            steps.forEach(s -> s.before.remove((Character) next.name));
        }
        return result.toString();
    }

    public static int part2(List<String> input, int workers) {
        List<Step> steps = getSteps(input);
        int totalTime = 0;
        while (!steps.isEmpty()) {
            List<Step> next = steps.stream()
                    .filter(s -> s.before.isEmpty())
                    .sorted(Comparator.comparingInt(s -> s.time.get()))
                    .limit(workers)
                    .toList();

            next.forEach(Step::decrementTime);
            steps.forEach(s -> s.before.removeAll(
                    steps.stream().filter(p -> p.time.get() == 0).map(Step::name).toList()
            ));
            steps.removeIf(s -> s.time.get() == 0);
            totalTime++;
        }
        return totalTime;
    }

    private static List<Step> getSteps(List<String> input) {
        List<Step> steps = new ArrayList<>();

        for (String line : input) {
            char[] chars = line.substring(1).replaceAll("[^A-Z]", "").toCharArray();
            Step step = steps.stream()
                    .filter(s -> s.name == chars[1])
                    .findFirst()
                    .orElseGet(() -> {
                        Step newStep = new Step(chars[1]);
                        steps.add(newStep);
                        return newStep;
                    });
            step.before.add(chars[0]);
        }

        steps.addAll(steps.stream()
                .flatMap(s -> s.before.stream())
                .distinct()
                .filter(c -> steps.stream().noneMatch(s -> s.name == c))
                .map(Step::new)
                .toList());
        return steps;
    }

    record Step(char name, AtomicInteger time, List<Character> before) {
        public Step(char name) {
            this(name, new AtomicInteger(name - 'A' + 61), new ArrayList<>());
        }

        void decrementTime() {
            time.decrementAndGet();
        }
    }
}
