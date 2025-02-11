package org.anne.aoc2022;

import org.anne.common.Day;

public class Day6 extends Day{
    public static void main(String[] args) {
        new Day6().run();
    }

    @Override
    public void execute() {
        setName("Tuning Trouble");
        String input = readFileOneLine();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(String input) {
        for (int i = 4; i < input.length(); i++) {
            if (input.substring(i-4, i).chars().distinct().count() == 4) {
                return i;
            }
        }
        return 0;
    }

    public static int part2(String input) {
        for (int i = 14; i < input.length(); i++) {
            if (input.substring(i-14, i).chars().distinct().count() == 14) {
                return i;
            }
        }
        return 0;
    }
}
