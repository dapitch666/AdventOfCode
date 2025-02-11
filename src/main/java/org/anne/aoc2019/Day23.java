package org.anne.aoc2019;

import org.anne.common.Day;


public class Day23 extends Day {
    public static void main(String[] args) {
        new Day23().run();
    }

    @Override
    public void execute() {
        setName("Category Six");
        String input = readFileOneLine();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static long part1(String input) {
        return getPacketValue(input, true);
    }

    public static long part2(String input) {
        return getPacketValue(input, false);
    }

    private static long getPacketValue(String input, boolean returnFirstSentToNat) {
        Computer[] computers = new Computer[50];
        for (int i = 0; i < computers.length; i++) {
            computers[i] = new Computer(input);
            computers[i].writeInput(i);
        }
        long[] nat = new long[2];
        long lastSent = -1;
        while (true) {
            boolean networkIsIdle = true;
            for (Computer computer : computers) {
                computer.compute();
                if (computer.hasOutput()) {
                    networkIsIdle = false;
                    while (computer.hasOutput()) {
                        int address = (int) computer.getOutput();
                        long x = computer.getOutput();
                        long y = computer.getOutput();
                        if (address == 255) {
                            nat[0] = x;
                            nat[1] = y;
                            if (returnFirstSentToNat) {
                                return y;
                            }
                        } else {
                            computers[address].writeInput(x, y);
                        }
                    }
                } else {
                    computer.writeInput(-1);
                }
            }
            if (networkIsIdle) {
                if (lastSent == nat[1]) {
                    return nat[1];
                }
                lastSent = nat[1];
                computers[0].writeInput(nat);
            }
        }
    }
}
