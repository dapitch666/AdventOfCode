package org.anne.aoc2015;

import org.anne.common.Day;
import org.anne.common.Utils;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Day12 extends Day {
    public static void main(String[] args) {
        new Day12().run();
    }

    @Override
    public void execute() {
        setName("JSAbacusFramework.io");
        String input = readFileOneLine();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(String input) {
        return sumValues(input);
    }

   public static int part2(String input) {
       return sumBraces(input);
   }

    private static int sumBraces(String input) {
        Pattern pattern = Pattern.compile("\\{[^{}]*}");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            input = matcher.replaceAll(Day12::reduceBraces);
            matcher = pattern.matcher(input);
        }
        return sumValues(input);
    }

    private static int sumValues(String input) {
        return Utils.inputToIntStream(input).sum();
    }

    private static String reduceBraces(MatchResult match) {
        String value = match.group();
        return value.contains(":\"red\"") ? "0" : String.valueOf(sumValues(value));
    }
}
