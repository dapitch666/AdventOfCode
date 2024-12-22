package org.anne.aoc2024;

import org.anne.common.Day;

import java.util.*;
import java.util.stream.Collectors;

public class Day11 extends Day {
    public static void main(String[] args) {
        new Day11().run();
    }

    @Override
    public void execute() {
        setName("Plutonian Pebbles");
        String input = readFileOneLine();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static long part1(String input) {
        return process(input, 25);
    }

    public static long part2(String input) {
        return process(input, 75);
    }

    public static long process(String input, int iterations) {
        Map<Long, Long> stones = Arrays.stream(input.split(" "))
                .map(Long::parseLong)
                .collect(Collectors.toMap(l -> l, l -> 1L));
        for (int i = 0; i < iterations; i++) {
            Map<Long, Long> newStones = new HashMap<>();
            stones.forEach((stone, count) ->
                blink(stone).forEach(newStone -> newStones.merge(newStone, count, Long::sum))
            );
            stones = newStones;
        }
        return stones.values().stream().mapToLong(Long::longValue).sum();
    }

    static List<Long> blink(long stone) {
        if (stone == 0L) {
            return List.of(1L);
        }
        String stoneStr = Long.toString(stone);
        int length = stoneStr.length();
        if (length % 2 == 0) {
            return List.of(
                    Long.parseLong(stoneStr.substring(0, length / 2)),
                    Long.parseLong(stoneStr.substring(length / 2))
            );
        }
        return List.of(stone * 2024);
    }
}
