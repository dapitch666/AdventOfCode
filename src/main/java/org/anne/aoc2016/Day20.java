package org.anne.aoc2016;

import org.anne.common.Day;

import java.util.Comparator;
import java.util.List;

public class Day20 extends Day {
    public static void main(String[] args) {
        new Day20().run();
    }

    @Override
    public void execute() {
        setName("Firewall Rules");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input, 4294967295L));
    }

    public static long part1(List<String> input) {
        List<Range> ranges = getRanges(input);
        long min = ranges.getFirst().end + 1;
        for (Range range : ranges) {
            if (range.start > min) {
                return min;
            } else {
                min = Math.max(min, range.end + 1);
            }
        }
        return min;
    }

    public static long part2(List<String> input, long max) {
        List<Range> ranges = getRanges(input);
        long count = 0;
        long current = 0;
        for (Range range : ranges) {
            if (range.start > current) {
                count += range.start - current;
            }
            current = Math.max(current, range.end + 1);
        }
        if (current <= max) {
            count += max - current + 1;
        }
        return count;
    }

    private static List<Range> getRanges(List<String> input) {
        return input.stream()
                .map(line -> {
                    String[] parts = line.split("-");
                    return new Range(Long.parseLong(parts[0]), Long.parseLong(parts[1]));
                })
                .sorted(Comparator.comparingLong(r -> r.start))
                .toList();
    }

    record Range(long start, long end) {
    }
}
