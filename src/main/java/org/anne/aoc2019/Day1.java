package org.anne.aoc2019;


import org.anne.common.Day;

import java.util.List;

public class Day1 extends Day {

    public static void main(String[] args) {
        Day day = new Day1();
        List<Integer> input = day.readFileAsInts();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
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
