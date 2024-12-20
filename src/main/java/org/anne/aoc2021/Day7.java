package org.anne.aoc2021;

import org.anne.common.Day;

import java.util.List;
import java.util.function.Function;

public class Day7 extends Day {

    public static void main(String[] args) {
        Day day = new Day7();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("The Treachery of Whales");
        List<Integer> input = this.readFileIntegerOneLine();
        this.setPart1(part1(input));
        this.setPart2(part2(input));
        this.printParts();
    }

    public static int part1(List<Integer> input) {
        return minFuel(input, dist -> dist);
    }

    public static int part2(List<Integer> input) {
        return minFuel(input, dist -> (dist * (dist + 1)) / 2);
    }

    private static int minFuel(List<Integer> input, Function<Integer, Integer> fuelConsumption) {
        int maxPosition = input.stream().mapToInt(i -> i).max().orElse(0);
        int minFuel = Integer.MAX_VALUE;
        for (int j = 0; j <= maxPosition; j++) {
            int finalJ = j;
            int fuel = input.stream().mapToInt(i -> fuelConsumption.apply(Math.abs(finalJ - i))).sum();
            minFuel = Math.min(minFuel, fuel);
        }
        return minFuel;
    }
}
