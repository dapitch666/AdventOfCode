package org.anne.aoc2024;

import org.anne.common.Day;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 extends Day {
    public static void main(String[] args) {
        Day day = new Day3();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Mull It Over");
        String input = String.join("", this.readFile());
        this.setPart1(part1(input));
        this.setPart2(part2(input));
        this.printParts();
    }

    public static int part1(String input) {
        return getMult(input);
    }

    public static int part2(String input) {
        // Processes the input string by splitting it on the delimiter "do()",
        // then further splits each part on the first occurrence of the delimiter "don't()".
        return Arrays.stream(input.split("do\\(\\)"))
                .map(part -> part.split("don't\\(\\)", 2)[0])
                .mapToInt(Day3::getMult)
                .sum();
    }

    private static int getMult(String input) {
        int result = 0;
        Matcher matcher = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)").matcher(input);
        while (matcher.find()) {
            result += Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2));
        }
        return result;
    }
}
