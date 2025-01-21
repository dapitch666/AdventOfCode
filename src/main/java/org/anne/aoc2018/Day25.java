package org.anne.aoc2018;

import org.anne.common.Day;
import org.anne.common.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day25 extends Day {
    public static void main(String[] args) {
        new Day25().run();
    }

    @Override
    public void execute() {
        setName("Four-Dimensional Adventure");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2());
        printParts();
    }

    public static int part1(List<String> input) {
        List<List<int[]>> constellations = new ArrayList<>();
        for (String line : input) {
            int[] point = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
            List<Integer> joined = new ArrayList<>();
            for (int i = 0; i < constellations.size(); i++) {
                if (constellations.get(i).stream().anyMatch(p -> Utils.manhattanDistance(point, p) <= 3)) {
                    constellations.get(i).add(point);
                    joined.add(i);
                }
            }
            List<int[]> constellation = new ArrayList<>();
            for (int i = joined.size() - 1; i >= 0; i--) {
                constellation.addAll(constellations.remove((int) joined.get(i)));
            }
            constellation.add(point);
            constellations.add(constellation);
        }
        return constellations.size();
    }

    public static String part2() {
        return "Merry Christmas!";
    }
}
