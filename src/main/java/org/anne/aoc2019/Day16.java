package org.anne.aoc2019;

import org.anne.common.Day;

import java.util.Arrays;
import java.util.stream.Collectors;


public class Day16 extends Day {

    public static void main(String[] args) {
        Day day = new Day16();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Flawed Frequency Transmission");
        String input = this.readFileOneLine();
        this.setPart1(part1(input));
        this.setPart2(part2(input));
        this.printParts();
    }

    static String part1(String input) {
        int[] pattern = new int[]{0, 1, 0, -1};
        for (int i = 0; i < 100; i++) {
            StringBuilder output = new StringBuilder();
            for (int j = 0; j < input.length(); j++) {
                int sum = 0;
                for (int k = 0; k < input.length(); k++) {
                    int patternIndex = (k + 1) / (j + 1) % 4;
                    sum += Character.getNumericValue(input.charAt(k)) * pattern[patternIndex];
                }
                output.append(Math.abs(sum) % 10);
            }
            input = output.toString();
        }
        return input.substring(0, 8);
    }

    static String part2(String input) {
        int offset = Integer.parseInt(input.substring(0, 7));
        int[] state = input.repeat(10000).chars().toArray();
        for (int i = 0; i < 100; i++) {
            int sum = 0;
            for (int j = state.length - 1; j >= offset; j--) {
                sum += state[j];
                state[j] = sum % 10;
            }
        }
        return Arrays.stream(state, offset, offset + 8)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining());
    }
}
