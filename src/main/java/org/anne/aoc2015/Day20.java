package org.anne.aoc2015;

import org.anne.common.Day;

import java.util.stream.IntStream;


public class Day20 extends Day {
    public static void main(String[] args) {
        new Day20().run();
    }

    @Override
    public void execute() {
        setName("Infinite Elves and Infinite Houses");
        int input = Integer.parseInt(readFileOneLine());
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(int input) {
        return findHouse(input, 10, Integer.MAX_VALUE);
    }

    public static int part2(int input) {
        return findHouse(input, 11, 50);
    }

    private static int findHouse(int input, int gifts, int maxDeliveries) {
        int[] houses = new int[input / gifts];
        for (int elf = 1; elf < houses.length; elf++) {
            for (int house = elf, deliveries = 0; house < houses.length && deliveries < maxDeliveries; house += elf, deliveries++) {
                houses[house] += elf * gifts;
            }
        }
        return IntStream.range(0, houses.length).filter(house -> houses[house] >= input).findFirst().orElseThrow();
    }
}
