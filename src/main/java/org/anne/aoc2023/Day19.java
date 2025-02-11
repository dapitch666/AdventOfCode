package org.anne.aoc2023;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day19 extends Day {
    public static void main(String[] args) {
        new Day19().run();
    }

    @Override
    public void execute() {
        setName("Aplenty");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }


    public static int part1(List<String> input) {
        var workflows = parseWorkflow(input);
        var parts = parseParts(input);
        var rating = 0;
        
        for (var part : parts) {
            var currentWorkflow = workflows.get("in");
            var currentDestination = "";
            while (!currentDestination.equals("A") && !currentDestination.equals("R")) {
                for (var rule : currentWorkflow.rules) {
                    if (rule.getPredicate().test(part.get(rule.category))) {
                        currentDestination = rule.destination;
                        break;
                    }
                }
                currentWorkflow = workflows.get(currentDestination);
            }
            if (currentDestination.equals("A")) {
                rating += part.rating();
            }
        }
        return rating;
    }

    public static long part2(List<String> input) {
        var workflows = parseWorkflow(input);
        HashMap<Character, Boundary> characterBoundaries = new HashMap<>();
        for (char c : "xmas".toCharArray()) {
            characterBoundaries.put(c, new Boundary(0, 4001));
        }
        return calculateBoundaries(workflows, characterBoundaries, "in");
    }
    
    record Rule(Character category, String operator, int value, String destination) {
        public Rule(String defaultDestination) {
            this('o', "", 0, defaultDestination);
        }

        Predicate<Integer> getPredicate() {
            return switch (operator) {
                case ">" -> i -> i > value;
                case "<" -> i -> i < value;
                default -> i -> true;
            };
        }
    }
    
    record Workflow(String name, List<Rule> rules) { }
    record Boundary(long min, long max) { }
    
    record Part(int x, int m, int a, int s) {
        int get(char category) {
            return switch (category) {
                case 'x' -> x;
                case 'm' -> m;
                case 'a' -> a;
                case 's' -> s;
                case 'o' -> 0;
                default -> throw new IllegalArgumentException("Unknown category: " + category);
            };
        }
        int rating() {
            return x + m + a + s;
        }
    }
    
    private static long calculateBoundaries(Map<String, Workflow> workflows, Map<Character, Boundary> categoryBoundaries, String start) {
        long sum = 0L;
        for (Rule rule : workflows.get(start).rules) {
            sum += applyRule(workflows, categoryBoundaries, rule);
        }
        return sum;
    }

    private static long countCombinations(Map<String, Workflow> workflows, Map<Character, Boundary> boundaries, Rule rule) {
        if (rule.destination.equals("A")) {
            long combinations = 1;
            for (Boundary boundary : boundaries.values()) {
                combinations *= boundary.max - boundary.min - 1;
            }
            return combinations;
        }
        else if (rule.destination.equals("R")) return 0;
        else return calculateBoundaries(workflows, boundaries, rule.destination);
    }

    private static long applyRule(Map<String, Workflow> workflows, Map<Character, Boundary> categoryBoundaries, Rule rule) {
        if (rule.category != 'o') {
            var newCategoryBoundaries = new HashMap<>(categoryBoundaries);
            var constraint = categoryBoundaries.get(rule.category);
            if (rule.operator.equals("<")) {
                newCategoryBoundaries.put(rule.category, new Boundary(constraint.min, Math.min(constraint.max, rule.value)));
                categoryBoundaries.put(rule.category, new Boundary(Math.max(constraint.min, rule.value - 1), constraint.max));
            } else {
                newCategoryBoundaries.put(rule.category, new Boundary(Math.max(constraint.min, rule.value), constraint.max));
                categoryBoundaries.put(rule.category, new Boundary(constraint.min, Math.min(constraint.max, rule.value + 1)));
            }
            return countCombinations(workflows, newCategoryBoundaries, rule);
        }
        return countCombinations(workflows, categoryBoundaries, rule);
    }

    private static List<Part> parseParts(List<String> input) {
        var partsPattern = Pattern.compile("^\\{x=(\\d+),m=(\\d+),a=(\\d+),s=(\\d+)}$");
        return input.stream().dropWhile(s -> !s.isBlank()).skip(1)
                .map(partsPattern::matcher)
                .filter(Matcher::matches)
                .map(m -> new Part(
                        Integer.parseInt(m.group(1)),
                        Integer.parseInt(m.group(2)),
                        Integer.parseInt(m.group(3)),
                        Integer.parseInt(m.group(4))))
                .toList();
    }

    private static Map<String, Workflow> parseWorkflow(List<String> input) {
        var workflowPattern = Pattern.compile("^([a-z]+)\\{(.*),([a-zAR]+)}$");
        var workflows = new HashMap<String, Workflow>();
        input.stream().takeWhile(s -> !s.isBlank()).forEach(s -> {
            var matcher = workflowPattern.matcher(s);
            if (matcher.matches()) {
                var name = matcher.group(1);
                var rules = parseRules(matcher.group(2), matcher.group(3));
                workflows.put(name, new Workflow(name, rules));
            }
        });
        return workflows;
    }

    static List<Rule> parseRules(String rulesString, String defaultDestination) {
        var pattern = Pattern.compile("([xmas])([<>])(\\d+):([a-zAR]+)");
        var rules = new ArrayList<>(Stream.of(rulesString.split(","))
                .map(pattern::matcher)
                .filter(Matcher::matches)
                .map(matcher -> {
                    char category = matcher.group(1).charAt(0);
                    String operator = matcher.group(2);
                    int value = Integer.parseInt(matcher.group(3));
                    String destination = matcher.group(4);
                    return new Rule(category, operator, value, destination);
                })
                .toList());
        rules.add(new Rule(defaultDestination));
        return rules;
    }
}
