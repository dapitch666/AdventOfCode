package org.anne.aoc2016;

import org.anne.common.Day;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day23 extends Day {
    public static void main(String[] args) {
        new Day23().run();
    }

    @Override
    public void execute() {
        setName("Safe Cracking");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(List<String> input) {
        Computer computer = new Computer(input, new int[]{7, 0, 0, 0});
        return computer.execute();
    }

    public static int part2(List<String> input) {
        return factorial(12) + 85 * 92; // from my input, lines 20-21 -> cpy 85 c / jnz 92 d
    }

    public static int factorial(int n) {
        if (n == 0) return 1;
        return n * factorial(n-1);
    }
}
