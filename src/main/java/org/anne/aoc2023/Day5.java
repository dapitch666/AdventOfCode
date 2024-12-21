package org.anne.aoc2023;

import org.anne.common.Day;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day5 extends Day {
    public static void main(String[] args) {
        Day day = new Day5();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("If You Give A Seed A Fertilizer");
        List<String> input = this.readFile();
        this.setPart1(part1(input));
        this.setPart2(part2(input));
        this.printParts();
    }


    public static long part1(List<String> input) {
        List<Long> locations = new ArrayList<>();
        var seeds = Arrays.stream(input.get(0).split(": ")[1].split(" ")).map(Long::parseLong).toList();
        var maps = getMaps(input.subList(2, input.size()));
        for (var seed : seeds) {
            var location = seed;
            for (var map : maps) {
                location = getNextLocation(location, map);
            }
            locations.add(location);
        }
        return locations.stream().mapToLong(Long::longValue).min().orElse(0);
    }

    
    public static long part2(List<String> input) {
        var seeds = Arrays.stream(input.get(0).split(": ")[1].split(" ")).map(Long::parseLong).toList();
        var maps = getMaps(input.subList(2, input.size()));
        List<Range> ranges = new ArrayList<>();
        for (int i = 0; i < seeds.size(); i += 2) {
            ranges.add(new Range(seeds.get(i), seeds.get(i) + seeds.get(i + 1)));
        }
        List<Range> tmp = new ArrayList<>();
        for (var map : maps) {
            for (var conversionMap : map) {
                for (int i = 0; i < ranges.size(); i++) {
                    Range seedRange = ranges.get(i);
                    if (conversionMap.sourceRangeStart <= seedRange.end &&
                            conversionMap.sourceRangeStart + conversionMap.rangeLength() > seedRange.start) {
                        
                        ranges.remove(i--);
                        
                        var delta = conversionMap.destinationRangeStart - conversionMap.sourceRangeStart;
                        var tmpRange = new Range(
                                Math.max(conversionMap.sourceRangeStart, seedRange.start),
                                Math.min(conversionMap.sourceRangeStart + conversionMap.rangeLength, seedRange.end));
                        var mappedRange = new Range(tmpRange.start + delta, tmpRange.end + delta);
                        tmp.add(mappedRange);
                        
                        if (seedRange.start < tmpRange.start) {
                            ranges.add(new Range(seedRange.start, tmpRange.start - 1));
                        }
                        if (seedRange.end > tmpRange.end) {
                            ranges.add(new Range(tmpRange.end, seedRange.end - 1));
                        }
                    }
                }
                
            }
            ranges.addAll(tmp);
            tmp.clear();
        }
        ranges.addAll(tmp);
        return ranges.stream().mapToLong(r -> r.start).min().orElse(0);
    }

    record ConversionMap(long destinationRangeStart, long sourceRangeStart, long rangeLength) {
        ConversionMap(List<Long> map) {
            this(map.get(0), map.get(1), map.get(2));
        }
    }

    record Range(long start, long end) {}

    private static Long getNextLocation(Long location, List<ConversionMap> conversionMaps) {
        for (var conversionMap : conversionMaps) {
            if (location >= conversionMap.sourceRangeStart && location < conversionMap.sourceRangeStart + conversionMap.rangeLength) {
                return conversionMap.destinationRangeStart + location - conversionMap.sourceRangeStart;
            }
        }
        return location;
    }

    private static List<List<ConversionMap>> getMaps(List<String> input) {
        List<List<ConversionMap>> maps = new ArrayList<>();
        var pattern = Pattern.compile("^(\\d+) (\\d+) (\\d+)$");
        var map = new ArrayList<ConversionMap>();
        for (String line : input) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                map.add(new ConversionMap(Arrays.stream(line.split(" ")).map(Long::parseLong).toList()));
            } else if (line.isBlank()) {
                maps.add(map);
            } else {
                map = new ArrayList<>();
            }
        }
        maps.add(map);
        return maps;
    }
}
