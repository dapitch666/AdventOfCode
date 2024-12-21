package org.anne.aoc2019;

import org.anne.common.Day;

public class Day2 extends Day {

    public static void main(String[] args) {
        Day day = new Day2();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("1202 Program Alarm");
        String input = this.readFileOneLine();
        this.setPart1(part1(input));
        this.setPart2(part2(input));
        this.printParts();
    }

    static long part1(String input) {
        Computer computer = new Computer(input);
        computer.compute(12, 2);
        long[] intCode = computer.getIntCode();
        return intCode[0];
    }

    static int part2(String input) {
        int okNoun = 0;
        int okVerb = 0;
        for (int noun = 0; noun < 100; noun++) {
            for (int verb = 0; verb < 100; verb++) {
                Computer computer = new Computer(input);
                computer.compute(noun, verb);
                if (computer.getIntCode()[0] == 19690720) {
                    okNoun = noun;
                    okVerb = verb;
                    break;
                }
            }
        }
        return 100 * okNoun + okVerb;
    }
}
