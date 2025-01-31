package org.anne.aoc2017;

import org.anne.common.Day;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.anne.aoc2017.KnotHash.knotHash;
import static org.anne.aoc2017.KnotHash.sparseHash;

public class Day10 extends Day {
    public static void main(String[] args) {
        new Day10().run();
    }

    @Override
    public void execute() {
        setName("Knot Hash");
        String input = readFileOneLine();
        setPart1(part1(input, 256));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(String input, int size) {
        List<Integer> lengths = Stream.of(input.split(",")).map(Integer::parseInt).toList();
        List<Integer> list = IntStream.range(0, size).boxed().collect(Collectors.toList());
        sparseHash(lengths, list, 1);
        return list.get(0) * list.get(1);
    }

    public static String part2(String input) {
        return knotHash(input);
    }
}
