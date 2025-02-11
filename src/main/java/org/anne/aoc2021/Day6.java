package org.anne.aoc2021;

import org.anne.common.Day;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day6 extends Day {

    public static void main(String[] args) {
        new Day6().run();
    }

    @Override
    public void execute() {
        setName("Lanternfish");
        List<Integer> input = readFileIntegerOneLine(",");
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static long part1(List<Integer> input) {
        return fishGrowth(input, 80);
    }

    public static long part2(List<Integer> input) {
        return fishGrowth(input, 256);
    }

    private static long fishGrowth(List<Integer> input, int days) {
        Map<Integer, Long> fish = input.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for (int d = 0; d < days; d++) {
            Map<Integer, Long> newFish = new HashMap<>();
            Long babies = fish.getOrDefault(0, 0L);
            newFish.put(8, babies);
            for(Map.Entry<Integer, Long> fishGroup : fish.entrySet()) {
                int key = fishGroup.getKey() == 0 ? 6 : fishGroup.getKey() - 1;
                newFish.merge(key, fishGroup.getValue(), Long::sum);
            }
            fish = newFish;
        }
        return fish.values().stream().mapToLong(i->i).sum();
    }
}
