package org.anne.aoc2016;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.List;

public class Day15 extends Day {
    public static void main(String[] args) {
        new Day15().run();
    }

    @Override
    public void execute() {
        setName("Timing is Everything");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(List<String> input) {
        return getWinningTime(getDiscs(input));
    }

    public static int part2(List<String> input) {
        List<Disc> discs = new ArrayList<>(getDiscs(input));
        discs.add(new Disc(11, 0));
        return getWinningTime(discs);
    }

    private static int getWinningTime(List<Disc> discs) {
        for (int time = 0; ; time++) {
            boolean success = true;
            for (int i = 0; i < discs.size(); i++) {
                if (!discs.get(i).isOpen(time + i + 1)) {
                    success = false;
                    break;
                }
            }
            if (success) {
                return time;
            }
        }
    }

    private static List<Disc> getDiscs(List<String> input) {
        return input.stream()
                .map(s -> s.replace(".", "").split(" "))
                .map(s -> new Disc(Integer.parseInt(s[3]), Integer.parseInt(s[11])))
                .toList();
    }

    record Disc(int positions, int initialPosition) {
        boolean isOpen(int time) {
            return (initialPosition + time) % positions == 0;
        }
    }
}
