package org.anne.aoc2016;

import org.anne.common.Day;

import java.util.List;

public class Day25 extends Day {
    public static void main(String[] args) {
        new Day25().run();
    }

    @Override
    public void execute() {
        setName("Clock Signal");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2());
        printParts();
    }

    public static int part1(List<String> input) {
        AssemBunny assemBunny = new AssemBunny(input);
        int i = 0;
        while (true) {
            assemBunny.setRegister('a', i);
            assemBunny.run();
            if (assemBunny.getOutput().equals("0101010101")) {
                return i;
            }
            i++;
        }
    }

    public static String part2() {
        return "Merry Christmas!";
    }
}
