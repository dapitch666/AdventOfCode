package org.anne.aoc2022;

import org.anne.common.Day;
import org.anne.common.Utils;

import java.awt.*;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day15 extends Day {
    public static void main(String[] args) {
        Day day = new Day15();
        List<String> input = day.readFile();
        day.setPart1(part1(input, 2000000));
        day.setPart2(part2(input, 4000000));
        day.printParts();
    }

    public static long part1(List<String> input, int rowNb) {
        List<Sensor> sensorList = getSensors(input);
        List<Range> ranges = getEmptyPositions(sensorList, rowNb);
        return ranges.stream().mapToInt(Range::size).sum();
    }

    public static long part2(List<String> input, int max) {
        List<Sensor> sensorList = getSensors(input);
        for(int row = 0; row < max; row++) {
            List<Range> ranges = getEmptyPositions(sensorList, row);
            if(ranges.size() > 1) {
                return (ranges.get(0).end + 1) * 4000000L + row;
            }
        }
        return 0;
    }

    record Sensor (Point sensor, int distance) {
        public boolean isInRange(int row) {
            return spareDistance(row) > 0;
        }
        public int spareDistance(int row) {
            return distance - Math.abs(sensor.y - row);
        }
    }
    record Range (int start, int end) {
        public int size() {
            return end - start;
        }
    }

    private static int manhattanDistance(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    private static List<Sensor> getSensors(List<String> input) {
        List<Sensor> sensorList = new ArrayList<>();
        Pattern pattern = Pattern.compile(".*x=(-*\\d+), y=(-*\\d+):.*x=(-*\\d+), y=(-*\\d+)");
        for (String line : input) {
            Matcher m = pattern.matcher(line);
            if (m.matches()) {
                Point s = new Point(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)));
                Point b = new Point(Integer.parseInt(m.group(3)), Integer.parseInt(m.group(4)));
                sensorList.add(new Sensor(s, manhattanDistance(s, b)));
            }
        }
        return sensorList;
    }

    public static List<Range> getEmptyPositions(List<Sensor> sensorList, int row) {
        List<Range> ranges = sensorList
                .stream()
                .filter(s -> s.isInRange(row))
                .map(s -> new Range(s.sensor().x - s.spareDistance(row), s.sensor().x + s.spareDistance(row)))
                .sorted(Comparator.comparingInt(r -> r.start))
                .collect(Collectors.toList());
        List<Range> reducedRanges = new ArrayList<>();
        int min = ranges.get(0).start;
        int max = ranges.get(0).end;
        for (Range range : ranges.subList(1, ranges.size())) {
            if (range.end <= max) {
                continue;
            }
            if (range.start <= max) {
                max = range.end;
                continue;
            }
            reducedRanges.add(new Range(min, max));
            min = range.start;
            max = range.end;
        }
        reducedRanges.add(new Range(min, max));
        return reducedRanges;
    }
}
