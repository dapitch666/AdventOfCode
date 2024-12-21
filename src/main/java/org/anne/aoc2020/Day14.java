package org.anne.aoc2020;

import org.anne.common.Day;

import java.util.*;

public class Day14 extends Day {

    public static void main(String[] args) {
        Day day = new Day14();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Docking Data");
        List<String> input = this.readFile();
        this.setPart1(part1(input));
        this.setPart2(part2(input));
        this.printParts();
    }

    public static long part1(List<String> input) {
        char[] mask = new char[36];
        Map<Integer, Long> memoryMap = new HashMap<>();
        for (String line : input) {
            String[] l = line.split(" = ");
            if (l[0].equals("mask")) {
                mask = l[1].toCharArray();
            } else {
                int memIdx = Integer.parseInt(l[0].replaceAll("mem\\[(\\d+)]", "$1"));
                char[] memory = longToBinaryStringLeadingZeros(Long.parseLong(l[1]));
                for (int i = 0; i < 36; i++) {
                    if (mask[i] != 'X') {
                        memory[i] = mask[i];
                    }
                }
                memoryMap.put(memIdx, Long.parseLong(String.valueOf(memory), 2));
            }
        }
        return memoryMap.values().stream().mapToLong(Long::longValue).sum();
    }

    public static long part2(List<String> input) {
        char[] mask = new char[36];
        Map<Long, Long> memoryMap = new HashMap<>();
        for (String line : input) {
            String[] l = line.split(" = ");
            if (l[0].equals("mask")) {
                mask = l[1].toCharArray();
            } else {
                long memIdx = Long.parseLong(l[0].replaceAll("mem\\[(\\d+)]", "$1"));
                long value = Long.parseLong(l[1]);

                char[] binaryString = longToBinaryStringLeadingZeros(memIdx);

                Set<Long> memories = getMemories(binaryString, 0, mask);
                for (long key : memories) {
                    memoryMap.put(key, value);
                }
            }
        }
        return memoryMap.values().stream().mapToLong(Long::longValue).sum();
    }

    private static char[] longToBinaryStringLeadingZeros(long val) {
        String binaryString = Long.toBinaryString(val);
        return ("000000000000000000000000000000000000" + binaryString).substring(binaryString.length()).toCharArray();
    }

    private static Set<Long> getMemories(char[] mem, int i, char[] mask) {
        Set<Long> set = new HashSet<>();
        if (i == 36) {
            set.add(Long.parseLong(String.valueOf(mem), 2));
            return set;
        }
        switch (mask[i]) {
            case '0' -> set.addAll(getMemories(mem, i + 1, mask));
            case '1' -> {
                mem[i] = '1';
                set.addAll(getMemories(mem, i + 1, mask));
            }
            default -> {
                char[] memA = mem.clone(), memB = mem.clone();
                memA[i] = '1';
                memB[i] = '0';
                set.addAll(getMemories(memA, i + 1, mask));
                set.addAll(getMemories(memB, i + 1, mask));
            }
        }
        return set;
    }
}
