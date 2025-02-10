package org.anne.aoc2016;

import org.anne.common.Day;

import java.util.List;

public class Day12 extends Day {
    public static void main(String[] args) {
        new Day12().run();
    }

    @Override
    public void execute() {
        setName("Leonardo's Monorail");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(List<String> input) {
        AssemBunny assemBunny = new AssemBunny(input);
        assemBunny.run();
        return assemBunny.getRegisterA();
    }

    public static int part2(List<String> input) {
        AssemBunny assemBunny = new AssemBunny(input);
        assemBunny.setRegister('c', 1);
        assemBunny.run();
        return assemBunny.getRegisterA();
    }
}