package org.anne.aoc2015;

import org.anne.common.Day;

import java.util.List;

public class Day2 extends Day {
    public static void main(String[] args) {
        new Day2().run();
    }

    @Override
    public void execute() {
        setName("I Was Told There Would Be No Math");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(List<String> input) {
        return input.stream()
                .map(line -> line.split("x"))
                .mapToInt(dimensions -> new Box(
                        Integer.parseInt(dimensions[0]),
                        Integer.parseInt(dimensions[1]),
                        Integer.parseInt(dimensions[2]))
                        .wrappingPaper())
                .sum();
    }

    public static int part2(List<String> input) {
        return input.stream()
                .map(line -> line.split("x"))
                .mapToInt(dimensions -> new Box(
                        Integer.parseInt(dimensions[0]),
                        Integer.parseInt(dimensions[1]),
                        Integer.parseInt(dimensions[2]))
                        .ribbon())
                .sum();
    }

    record Box(int l, int w, int h) {
        int wrappingPaper() {
            int lw = l * w;
            int wh = w * h;
            int hl = h * l;
            return 2 * lw + 2 * wh + 2 * hl + Math.min(Math.min(lw, wh), hl);
        }

        int ribbon() {
            return 2 * Math.min(Math.min(l + w, w + h), h + l) + l * w * h;
        }
    }
}
