package org.anne.aoc2017;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class KnotHash {
    static String knotHash(String input) {
        List<Integer> lengths = input.chars().boxed().collect(Collectors.toList());
        lengths.addAll(List.of(17, 31, 73, 47, 23));
        List<Integer> list = IntStream.range(0, 256).boxed().collect(Collectors.toList());
        sparseHash(lengths, list, 64);
        return denseHash(list);
    }

    private static String denseHash(List<Integer> list) {
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

    static void sparseHash(List<Integer> lengths, List<Integer> list, int rounds) {
        int currentPosition = 0;
        int skipSize = 0;
        for (int round = 0; round < rounds; round++) {
            for (int length : lengths) {
                reverseSection(list, currentPosition, length);
                currentPosition = (currentPosition + length + skipSize) % list.size();
                skipSize++;
            }
        }
    }

    private static void reverseSection(List<Integer> list, int start, int length) {
        for (int i = 0; i < length / 2; i++) {
            int index1 = (start + i) % list.size();
            int index2 = (start + length - i - 1) % list.size();
            int temp = list.get(index1);
            list.set(index1, list.get(index2));
            list.set(index2, temp);
        }
    }

    static String knotHashBinary(String input) {
        return knotHash(input).chars().map(c -> Integer.parseInt(String.valueOf((char) c), 16))
                .mapToObj(i -> String.format("%4s", Integer.toBinaryString(i)).replace(' ', '0'))
                .collect(Collectors.joining());
    }
}