package org.anne.aoc2015;

import org.anne.common.Day;

import java.util.List;

public class Day17 extends Day {
    public static void main(String[] args) {
        new Day17().run();
    }

    @Override
    public void execute() {
        setName("No Such Thing as Too Much");
        List<Integer> input = readFileAsInts();
        setPart1(part1(input, 150));
        setPart2(part2(input, 150));
    }

    public static int part1(List<Integer> input, int target) {
        return findNbCombinations(input, target, false);
    }

    public static int part2(List<Integer> input, int target) {
        return findNbCombinations(input, target, true);
    }

    private static int findNbCombinations(List<Integer> input, int target, boolean usingMinContainers) {
        int minContainers = Integer.MAX_VALUE;
        int count = 0;

        for (int i = 0; i < (1 << input.size()); i++) {
            int sum = 0;
            int numContainers = 0;

            for (int j = 0; j < input.size(); j++) {
                if ((i & (1 << j)) > 0) {
                    sum += input.get(j);
                    numContainers++;
                }
            }

            if (sum == target) {
                if (usingMinContainers && numContainers < minContainers) {
                    minContainers = numContainers;
                    count = 1;
                } else if (!usingMinContainers || numContainers == minContainers) {
                    count++;
                }
            }
        }

        return count;
    }
}
