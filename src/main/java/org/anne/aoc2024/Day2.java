package org.anne.aoc2024;

import org.anne.common.Day;
import org.anne.common.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day2 extends Day {
    public static void main(String[] args) {
        Day day = new Day2();
        day.setName("Red-Nosed Reports");
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    public static int part1(List<String> input) {
        int result = 0;
        for (String line : input) {
            List<Integer> levels = Utils.inputToIntStream(line)
                    .boxed()
                    .toList();
            if (isValid(levels)) {
                result++;
            }
        }
        return result;
    }

    public static int part2(List<String> input) {
        int result = 0;
        for (String line : input) {
            List<Integer> levels = Utils.inputToIntStream(line)
                    .boxed()
                    .toList();
            if (isValid(levels)) {
                result++;
            } else {
                for (int i = 0; i < levels.size(); i++) {
                    var truncatedLevels = new ArrayList<>(levels.subList(0, i));
                    truncatedLevels.addAll(levels.subList(i + 1, levels.size()));
                    if (isValid(truncatedLevels)) {
                        result++;
                        break;
                    }
                }
            }
        }
        return result;
    }

    static boolean isValid(List<Integer> levels) {
        if (!levels.equals(levels.stream().sorted().toList()) && !levels.equals(levels.stream().sorted(Collections.reverseOrder()).toList())) {
            return false;
        }
        for (var i = 0; i < levels.size() - 1; i++) {
            var difference = Math.abs(levels.get(i) - levels.get(i + 1));
            if (difference == 0 || difference > 3) {
                return false;
            }
        }
        return true;
    }
}
