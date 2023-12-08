package org.anne.aoc2023;

import org.anne.common.Day;
import org.anne.common.MathsUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day8 extends Day {
    public static void main(String[] args) {
        Day day = new Day8();
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }


    public static int part1(List<String> input) {
        String instructions = input.get(0);
        Map<String, String[]> nodes = getNodeMap(input);
        return getMinSteps(nodes, instructions, "AAA", s -> s.equals("ZZZ"));
    }

    public static long part2(List<String> input) {
        String instructions = input.get(0);
        Map<String, String[]> nodes = getNodeMap(input);
        return nodes.keySet()
                .stream()
                .filter(node -> node.endsWith("A"))
                .mapToLong(node -> getMinSteps(nodes, instructions, node, s -> s.endsWith("Z")))
                .reduce(1, MathsUtils::lcm);
    }

    private static Map<String, String[]> getNodeMap(List<String> input) {
        Pattern pattern = Pattern.compile("(\\w+) = \\((\\w+), (\\w+)\\)");
        return input.stream()
                .map(pattern::matcher)
                .filter(Matcher::matches)
                .collect(Collectors.toMap(
                        matcher -> matcher.group(1),
                        matcher -> new String[]{matcher.group(2), matcher.group(3)}
                ));
    }

    private static int getMinSteps(Map<String, String[]> nodes, String instructions, String start,
                                   Predicate<String> stopCondition) {
        int steps = 0;
        int instructionLength = instructions.length();
        String currentNode = start;
        do {
            int instruction = instructions.charAt(steps % instructionLength);
            steps++;
            currentNode = instruction == 'L' ? nodes.get(currentNode)[0] : nodes.get(currentNode)[1];
        } while (!stopCondition.test(currentNode));
        return steps;
    }
}
