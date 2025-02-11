package org.anne.aoc2017;

import org.anne.common.Day;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.max;
import static java.util.Collections.min;

public class Day2 extends Day {
    public static void main(String[] args) {
        new Day2().run();
    }

    @Override
    public void execute() {
        setName("Corruption Checksum");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(List<String> input) {
        int sum = 0;
        for (String line : input) {
            List<Integer> numbers = Arrays.stream(line.split("\\s+")).map(Integer::parseInt).toList();
            sum += max(numbers) - min(numbers);
        }
        return sum;
    }

    public static int part2(List<String> input) {
        int sum = 0;
        for (String line : input) {
            List<Integer> numbers = Arrays.stream(line.split("\\s+")).map(Integer::parseInt).toList();
            for (int i = 0; i < numbers.size(); i++) {
                for (int j = 0; j < numbers.size(); j++) {
                    if (i != j && numbers.get(i) % numbers.get(j) == 0) {
                        sum += numbers.get(i) / numbers.get(j);
                    }
                }
            }
        }
        return sum;
    }
}
