package org.anne.aoc2024;

import org.anne.common.Day;

import java.util.Arrays;
import java.util.List;

public class Day22 extends Day {
    public static void main(String[] args) {
        new Day22().run();
    }

    public void execute() {
        setName("Monkey Market");
        List<Integer> input = readFileAsInts();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static long part1(List<Integer> input) {
        long result = 0L;
        for (long number : input) {
            for (int i = 0; i < 2000; i++) {
                number = hash(number);
            }
            result += number;
        }
        return result;
    }

    public static long part2(List<Integer> input) {
        long[] result = new long[130321]; // 130321 = 19^4
        int[] seen = new int[130321];
        int id = 0;
        for (int initial : input) {
            long first = hash(initial);
            long second = hash(first);
            long current = hash(second);
            Sequence sequence = new Sequence(0, diff(initial, first), diff(first, second), diff(second, current));

            for (int i = 3; i < 2000; i++) {
                long previous = current;
                current = hash(current);
                sequence = new Sequence(sequence.b, sequence.c, sequence.d, diff(previous, current));
                int key = sequence.key();

                if (seen[key] != id + 1) {
                    result[key] += current % 10;
                    seen[key] = id + 1;
                }
            }
            id++;
        }
        return Arrays.stream(result).max().orElse(0);
    }

    private static long hash(long n) {
        n = (n ^ (n << 6)) & 0xffffff;
        n = (n ^ (n >> 5)) & 0xffffff;
        return (n ^ (n << 11)) & 0xffffff;
    }

    private static int diff(long previous, long current) {
        return (int) (9 + current % 10 - previous % 10); // from (-9 to 9) to (0 to 18)
    }

    record Sequence(int a, int b, int c, int d) {
        int key() {
            return 6859 * a + 361 * b + 19 * c + d; // 6859 = 19^3, 361 = 19^2, 19 = 19^1
        }
    }
}
