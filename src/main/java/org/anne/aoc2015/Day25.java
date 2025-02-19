package org.anne.aoc2015;

import org.anne.common.Day;

import java.util.List;

public class Day25 extends Day {
    public static void main(String[] args) {
        new Day25().run();
    }

    @Override
    public void execute() {
        setName("Let It Snow");
        List<Integer> input = readFileGetAllInts();
        setPart1(part1(input));
        setPart2(part2());
    }

    public static long part1(List<Integer> input) {
        long code = 20151125;
        int row = 1, column = 1;
        while (row != input.get(0) || column != input.get(1)) {
            code = (code * 252533) % 33554393;
            if (row == 1) {
                row = column + 1;
                column = 1;
            } else {
                row--;
                column++;
            }
        }
        return code;
    }

    public static String part2() {
        return "Merry Christmas!";
    }
}
