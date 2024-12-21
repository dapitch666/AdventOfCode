package org.anne.aoc2020;

import org.anne.common.Day;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day13 extends Day {

    public static void main(String[] args) {
        Day day = new Day13();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Shuttle Search");
        List<String> input = this.readFile();
        this.setPart1(part1(input));
        this.setPart2(part2(input));
        this.printParts();
    }

    public static long part1(List<String> input) {
        int myTime = Integer.parseInt(input.get(0));
        List<Integer> busses = Stream.of(input.get(1).split(","))
                .map(Day13::mapToInt)
                .toList();
        return minWait(busses, myTime);
    }

    public static long part2(List<String> input) {
        List<Integer> busses = Stream.of(input.get(1).split(","))
                .map(Day13::mapToInt)
                .collect(Collectors.toList());
        return minTime(busses);
    }

    private static long minTime(List<Integer> busses) {
        long time = 0L;
        long multiple = 1L;
        for(int i = 0; i < busses.size(); i++) {
            int bus = busses.get(i);
            if (bus > 0) {
                while ((time + i) % bus != 0) {
                    time += multiple;
                }
                multiple *= bus;
            }
        }
        return time;
    }

    private static int mapToInt(String s) {
        if (s.equals("x")) {
            return 0;
        } else {
            return Integer.parseInt(s);
        }
    }

    private static int minWait(List<Integer> busses, int myTime) {
        int curBus = 0;
        int minWait = Integer.MAX_VALUE;
        for (int bus : busses) {
            if (bus > 0) {
                int wait = (myTime / bus + 1) * bus - myTime;
                if (wait < minWait) {
                    minWait = wait;
                    curBus = bus;
                }
            }
        }
        return curBus * minWait;
    }
}
