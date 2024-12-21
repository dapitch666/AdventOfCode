package org.anne.aoc2020;


import org.anne.common.Day;

import java.util.List;

public class Day3 extends Day {

    public static void main(String[] args) {
        Day day = new Day3();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Toboggan Trajectory");
        List<String> input = this.readFile();
        this.setPart1(part1(input));
        this.setPart2(part2(input));
        this.printParts();
    }

    public static int part1(List<String> input) {
        return slope(input, 3, 1);
    }

    public static int part2(List<String> input) {
        return slope(input, 1, 1)
                * slope(input, 3, 1)
                * slope(input, 5, 1)
                * slope(input, 7, 1)
                * slope(input, 1, 2);
    }

    private static int slope(List<String> input, int right, int down) {
        int trees = 0;
        for (int i = down; i < input.size(); i += down) {
            int index = i * right / down;
            int multiplier = index / input.get(0).length() + 1;
            String line = input.get(i).trim().repeat(multiplier);
            if (line.charAt(index) == '#') {
                trees++;
            }
        }
        return trees;
    }
}
