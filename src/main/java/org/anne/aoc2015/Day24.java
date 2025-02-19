package org.anne.aoc2015;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day24 extends Day {
    public static void main(String[] args) {
        new Day24().run();
    }

    @Override
    public void execute() {
        setName("It Hangs in the Balance");
        List<Integer> input = readFileGetAllInts();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static long part1(List<Integer> input) {
        return calculateQE(getBestArrangement(input, 3));
    }

    public static long part2(List<Integer> input) {
        return calculateQE(getBestArrangement(input, 4));
    }

    private static List<Integer> getBestArrangement(List<Integer> parcels, int numGroups) {
        int target = parcels.stream().mapToInt(Integer::intValue).sum() / numGroups;
        int comboSize = 1;

        while (true) {
            List<List<Integer>> allCombos = new ArrayList<>();
            buildCombos(parcels, comboSize, 0, new ArrayList<>(), allCombos);

            var optimalGroup = allCombos.stream()
                    .filter(c -> c.stream().mapToInt(Integer::intValue).sum() == target)
                    .min(Comparator.comparingLong(Day24::calculateQE))
                    .orElse(null);

            if (optimalGroup != null) {
                return optimalGroup;
            }
            comboSize++;
        }
    }

    private static long calculateQE(List<Integer> group) {
        return group.stream()
                .mapToLong(Integer::longValue)
                .reduce(1L, (a, b) -> a * b);
    }

    private static void buildCombos(List<Integer> parcels, int size, int index, List<Integer> current, List<List<Integer>> allCombos) {
        if (current.size() == size) {
            allCombos.add(new ArrayList<>(current));
            return;
        }

        for (int i = index; i < parcels.size(); i++) {
            current.add(parcels.get(i));
            buildCombos(parcels, size, i + 1, current, allCombos);
            current.removeLast();
        }
    }
}
