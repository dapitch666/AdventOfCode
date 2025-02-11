package org.anne.aoc2017;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.List;

public class Day17 extends Day {
    public static void main(String[] args) {
        new Day17().run();
    }

    @Override
    public void execute() {
        setName("Spinlock");
        int input = Integer.parseInt(readFileOneLine());
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(int input) {
        List<Integer> buffer = new ArrayList<>(List.of(0));
        int position = 0;
        for (int i = 1; i <= 2017; i++) {
            position = (position + input) % i + 1;
            buffer.add(position, i);
        }
        return buffer.get((position + 1) % buffer.size());
    }

    public static int part2(int input) {
        int valueAt1 = -1;
        int position = 0;
        for (int i = 1; i <= 50_000_000; i++) {
            position = (position + input) % i + 1;
            if (position == 1) {
                valueAt1 = i;
            }
        }
        return valueAt1;
    }
}
