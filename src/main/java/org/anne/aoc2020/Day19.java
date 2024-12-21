package org.anne.aoc2020;

import org.anne.common.Day;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day19 extends Day {

    public static void main(String[] args) {
        Day day = new Day19();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Monster Messages");
        List<String> input = this.readFile();
        this.setPart1(part1(input));
        this.setPart2(part2(input));
        this.printParts();
    }

    public static long part1(List<String> input) {
        int SepIdx = input.indexOf("");
        Map<Integer, String> rules = input.subList(0, SepIdx).stream()
                .map(s -> s.split(": "))
                .collect(Collectors.toMap(a -> Integer.parseInt(a[0]), a -> a[1]));
        List<String> messages = input.subList(SepIdx + 1, input.size());
        return countValidMessages(rules, messages);
    }

    public static long part2(List<String> input) {
        int SepIdx = input.indexOf("");
        Map<Integer, String> rules = input.subList(0, SepIdx).stream()
                .map(s -> s.split(": "))
                .collect(Collectors.toMap(a -> Integer.parseInt(a[0]), a -> a[1]));
        List<String> messages = input.subList(SepIdx + 1, input.size());
        rules.put(8, "42 | 42 8");
        rules.put(11, "42 31 | 42 11 31");
        return countValidMessages(rules, messages);
    }

    private static int countValidMessages(Map<Integer, String> rules, List<String> messages) {
        for (Map.Entry<Integer, String> rule : rules.entrySet()) {
            rule.setValue(getRule(rule.getValue(), rules, 0));
        }
        int count = 0;
        for (String message : messages) {
            if (message.matches(rules.get(0))) {
                count++;
            }
        }
        return count;
    }

    private static String getRule(String rule, Map<Integer, String> rules, int counter) {
        if (counter > 30) {
            return "";
        }
        String[] ruleSplit = rule.split(" ");
        for (int i = 0; i < ruleSplit.length; ++i) {
            if (ruleSplit[i].matches("\\d+")) {
                String ruleResult = getRule(rules.get(Integer.parseInt(ruleSplit[i])), rules, counter++);
                if (ruleResult.length() > 1) {
                    ruleSplit[i] = "(" + ruleResult + ")";
                } else {
                    ruleSplit[i] = ruleResult;
                }
            }
        }
        return String.join("", ruleSplit).replaceAll("\"", "");
    }
}
