package org.anne.aoc2015;

import org.anne.common.Day;

import java.util.Arrays;
import java.util.List;

public class Day14 extends Day {
    public static void main(String[] args) {
        new Day14().run();
    }

    @Override
    public void execute() {
        setName("Reindeer Olympics");
        List<String> input = readFile();
        setPart1(part1(input, 2503));
        setPart2(part2(input, 2503));
    }

    public static int part1(List<String> input, int time) {
        return input.stream()
                .map(s -> new Reindeer(s.split(" ")))
                .mapToInt(r -> r.distance(time))
                .max()
                .orElseThrow();
    }

    public static int part2(List<String> input, int time) {
        List<Reindeer> reindeer = input.stream()
                .map(s -> new Reindeer(s.split(" ")))
                .toList();
        int[] points = new int[reindeer.size()];

        for (int i = 1; i <= time; i++) {
            int t = i;
            int maxDistance = reindeer.stream()
                    .mapToInt(r -> r.distance(t))
                    .max()
                    .orElseThrow();
            for (int r = 0; r < reindeer.size(); r++) {
                if (reindeer.get(r).distance(i) == maxDistance) {
                    points[r]++;
                }
            }
        }
        return Arrays.stream(points).max().orElseThrow();
    }

    record Reindeer(int speed, int flyTime, int restTime) {
        Reindeer(String[] line) {
            this(Integer.parseInt(line[3]), Integer.parseInt(line[6]), Integer.parseInt(line[13]));
        }

        int distance(int time) {
            int cycleTime = flyTime + restTime;
            return time / cycleTime * flyTime * speed + Math.min(time % cycleTime, flyTime) * speed;
        }
    }
}
