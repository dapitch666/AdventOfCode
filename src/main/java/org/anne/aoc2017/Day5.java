package org.anne.aoc2017;

import org.anne.common.Day;

import java.util.List;

public class Day5 extends Day {
    public static void main(String[] args) {
        new Day5().run();
    }

    @Override
    public void execute() {
        setName("A Maze of Twisty Trampolines, All Alike");
        List<Integer> input = readFileAsInts();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(List<Integer> input) {
        int[] offsets = input.stream().mapToInt(i -> i).toArray();
        int steps = 0, i = 0;
        while (i >= 0 && i < offsets.length) {
            i += offsets[i]++;
            steps++;
        }
        return steps;
    }

    public static int part2(List<Integer> input) {
        int[] offsets = input.stream().mapToInt(i -> i).toArray();
        int steps = 0, i = 0;
        while (i >= 0 && i < offsets.length) {
            int offset = offsets[i];
            offsets[i] = offset + (offset >= 3 ? -1 : 1);
            i += offset;
            steps++;
        }
        return steps;
    }
}
