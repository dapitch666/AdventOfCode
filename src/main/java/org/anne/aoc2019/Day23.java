package org.anne.aoc2019;

import org.anne.common.Day;


public class Day23 extends Day {

    public static void main(String[] args) {
        Day day = new Day23();
        String input = day.readFileOneLine();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    public static long part1(String input) {
        Computer[] computers = new Computer[50];
        for (int i = 0; i < computers.length; i++) {
            computers[i] = new Computer(input, 4096);
            computers[i].writeInput(i);
        }
        int counter = 0;
        while (true) {
            counter++;
            for (Computer computer : computers) {
                computer.compute();
                if (computer.hasOutput()) {
                    while (computer.hasOutput()) {
                        int address = (int) computer.getOutput();
                        long x = computer.getOutput();
                        long y = computer.getOutput();
                        if (address == 255) {
                            System.out.println(counter);
                            return y;
                        } else {
                            computers[address].writeInput(new long[]{x, y});
                        }
                    }
                } else {
                    computer.writeInput(-1);
                }
            }
        }
    }

    public static long part2(String input) {
        Computer[] computers = new Computer[50];
        for (int i = 0; i < computers.length; i++) {
            computers[i] = new Computer(input, 4096);
            computers[i].writeInput(i);
        }
        long[] nat = new long[2];
        long sentByNat = -1;
        while (true) {
            boolean idle = true;
            for (Computer computer : computers) {
                computer.compute();
                if (computer.hasOutput()) {
                    while (computer.hasOutput()) {
                        idle = false;
                        int address = (int) computer.getOutput();
                        long x = computer.getOutput();
                        long y = computer.getOutput();
                        if (address == 255) {
                            nat[0] = x;
                            nat[1] = y;
                        } else {
                            computers[address].writeInput(new long[]{x, y});
                        }
                    }
                } else {
                    computer.writeInput(-1);
                }
            }

            if (idle) {
                computers[0].writeInput(nat);
                if (sentByNat == nat[1]) {
                    return nat[1];
                }
                sentByNat = nat[1];
            }
        }
    }
}
