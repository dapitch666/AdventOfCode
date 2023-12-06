package org.anne.aoc2023;

import org.anne.common.Day;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day5 extends Day {
    public static void main(String[] args) {
        Day day = new Day5();
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    static List<String> mapNames = Arrays.asList("seed-to-soil",
            "soil-to-fertilizer",
            "fertilizer-to-water",
            "water-to-light",
            "light-to-temperature",
            "temperature-to-humidity",
            "humidity-to-location");

    public static long part1(List<String> input) {
        List<Long> locations = new ArrayList<>();
        var seeds = Arrays.stream(input.get(0).split(": ")[1].split(" ")).map(Long::parseLong).toList();
        var maps = getMaps(input.subList(2, input.size()));
        for (var seed : seeds) {
            var location = seed;
            for (var mapName : mapNames) {
                location = getNextLocation(location, maps.get(mapName));
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
        for (var map : maps.values()) {
            for (var conversionMap : map) {
                for (int i = 0; i < ranges.size(); i++) {
                    Range seedRange = ranges.get(i);
                    if (conversionMap.sourceRangeStart <= seedRange.end &&
                            conversionMap.sourceRangeStart + conversionMap.rangeLength() > seedRange.start) {
                        
                        ranges.remove(i--);
                        
                        var delta = conversionMap.destinationRangeStart - conversionMap.sourceRangeStart;
                        var tmpRange = new Range(Math.max(conversionMap.sourceRangeStart, seedRange.start), Math.min(conversionMap.sourceRangeStart + conversionMap.rangeLength, seedRange.end));
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

    private static Map<String, List<ConversionMap>> getMaps(List<String> input) {
        Map<String, List<ConversionMap>> maps = new HashMap<>();
        var mapPattern = Pattern.compile("^(?<name>\\w+-to-\\w+) map:$");
        var map = new ArrayList<ConversionMap>();
        for (String line : input) {
            Matcher matcher = mapPattern.matcher(line);
            if (matcher.find()) {
                if (!map.isEmpty()) {
                    maps.put(mapNames.get(maps.size()), map);
                    map = new ArrayList<>();
                }
            } else if (!line.isBlank()) {
                map.add(new ConversionMap(Arrays.stream(line.split(" ")).map(Long::parseLong).toList()));
            }
        }
        maps.put(mapNames.get(maps.size()), map);
        return maps;
    }
}
