package org.anne.aoc2023;

import org.anne.common.Day;

import java.util.Arrays;
import java.util.List;

public class Day6 extends Day{
    public static void main(String[] args) {
        new Day6().run();
    }

    @Override
    public void execute() {
        setName("Wait For It");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }


    public static long part1(List<String> input) {
        long[] time = Arrays.stream(input.get(0).split(":\\s+")[1].split("\\s+")).mapToLong(Long::parseLong).toArray();
        long[] distance = Arrays.stream(input.get(1).split(":\\s+")[1].split("\\s+")).mapToLong(Long::parseLong).toArray();
        long winWays = 1;
        for (int i = 0; i < time.length; i++) {
            var race = new Race (time[i], distance[i]);
            winWays *= race.winWays();
        }
        return winWays;
    }

    public static long part2(List<String> input) {
        var time = Long.parseLong(input.get(0).split(":\\s+")[1].replaceAll("\\s+", ""));
        var distance = Long.parseLong(input.get(1).split(":\\s+")[1].replaceAll("\\s+", ""));
        var race = new Race(time, distance);
        return race.winWays();
    }
    
    record Race(long time, long distance) {
        long winWays () {
            var discr = Math.sqrt(Math.pow(time, 2) - 4 * distance);
            var minTime = (long) Math.floor((time - discr) / 2);
            var maxTime = (long) Math.ceil((time + discr) / 2);
            return maxTime - minTime - 1;
        }
    }
}
