package org.anne.aoc2024;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day13 extends Day {
    public static void main(String[] args) {
        new Day13().run();
    }

    @Override
    public void execute() {
        setName("Claw Contraption");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static long part1(List<String> input) {
        return getTotalTokens(input, 0);
    }

    public static long part2(List<String> input) {
        return getTotalTokens(input, 10000000000000L);
    }

    private static long getTotalTokens(List<String> input, long offset) {
        long tokens = 0;
        Pattern pattern = Pattern.compile("(\\d{1,5})");
        List<Long> parts = new ArrayList<>();
        for (String line : input) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                parts.add(Long.parseLong(matcher.group(1)));
            }
        }
        for (int i = 0; i < parts.size(); i += 6) {
            tokens += minTokens(parts.get(i), parts.get(i+1), parts.get(i+2), parts.get(i+3),
                    parts.get(i+4) + offset, parts.get(i+5) + offset);
        }
        return tokens;
    }

    static long minTokens(long buttonAx, long buttonAy, long buttonBx, long buttonBy, long prizeX, long priceY) {
        /* Finding the result is equivalent to solving the following system of equations:
              buttonAx * aPress + buttonBx * bPress = prizeX
              buttonAy * aPress + buttonBy * bPress = priceY
         */
        long bPress = (prizeX * buttonAy - priceY * buttonAx) / (buttonBx * buttonAy - buttonBy * buttonAx);
        long aPress = (prizeX - bPress * buttonBx) / buttonAx;

        return (aPress * buttonAx + bPress * buttonBx == prizeX && aPress * buttonAy + bPress * buttonBy == priceY) ? 3 * aPress + bPress : 0;
    }
}
