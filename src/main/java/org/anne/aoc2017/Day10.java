package org.anne.aoc2017;

import org.anne.common.Day;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
        knotHash(lengths, list, size, 1);
        return list.get(0) * list.get(1);
    }

    public static String part2(String input) {
        List<Integer> lengths = input.chars().boxed().collect(Collectors.toList());
        lengths.addAll(List.of(17, 31, 73, 47, 23));
        List<Integer> list = IntStream.range(0, 256).boxed().collect(Collectors.toList());
        knotHash(lengths, list, 256, 64);
        StringBuilder hash = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int xor = list.get(i * 16);
            for (int j = 1; j < 16; j++) {
                xor ^= list.get(i * 16 + j);
            }
            hash.append(String.format("%02x", xor));
        }
        return hash.toString();
    }

    private static void knotHash(List<Integer> lengths, List<Integer> list, int size, int rounds) {
        int currentPosition = 0;
        int skipSize = 0;
        for (int round = 0; round < rounds; round++) {
            for (int length : lengths) {
                for (int i = 0; i < length / 2; i++) {
                    int index1 = (currentPosition + i) % size;
                    int index2 = (currentPosition + length - i - 1) % size;
                    int temp = list.get(index1);
                    list.set(index1, list.get(index2));
                    list.set(index2, temp);
                }
                currentPosition = (currentPosition + length + skipSize) % size;
                skipSize++;
            }
        }
    }
}
