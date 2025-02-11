package org.anne.aoc2019;


import org.anne.common.Day;

import java.util.List;

public class Day1 extends Day {
    public static void main(String[] args) {
        new Day1().run();
    }

    @Override
    public void execute() {
        setName("The Tyranny of the Rocket Equation");
        List<Integer> input = readFileAsInts();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    static int part1(List<Integer> input) {
        return input.stream().mapToInt(Day1::getFuel).sum();
    }

    static int part2(List<Integer> input) {
        return input.stream().mapToInt(Day1::fuelFuel).sum();
    }

    static int getFuel(int mass) {
        return mass / 3 - 2;
    }

    static int fuelFuel(int mass) {
        int fuel = getFuel(mass);
        int sum = fuel;
        while (getFuel(fuel) > 0) {
            fuel = getFuel(fuel);
            sum += fuel;
        }
        return sum;
    }
}
