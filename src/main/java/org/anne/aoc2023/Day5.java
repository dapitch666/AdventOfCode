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
        var seeds = Arrays.stream(input.get(0).split(": ")[1].split(" "))
                .map(Long::parseLong).toList();
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
        var seedList = Arrays.stream(input.get(0).split(": ")[1].split(" "))
                .map(Long::parseLong).toList();
        var maps = getMaps(input.subList(2, input.size()));
        for (long l = 0; l < 1000000000000L; l++) {
            long location = l;
            for (int i = mapNames.size() - 1; i >= 0; i--) {
                var mapName = mapNames.get(i);
                var conversionMaps = maps.get(mapName);
                location = getPreviousLocation(location, conversionMaps);
            }
            if (seedsContains(seedList, location)) {
                return l;
            }
        }
        return 0;
    }

    private static boolean seedsContains(List<Long> seedList, long location) {
        for (int i = 0; i < seedList.size(); i += 2) {
            if (location >= seedList.get(i) && location <= seedList.get(i) + seedList.get(i + 1)) {
                return true;
            }
        }
        return false;
    }

    record ConversionMap(long destinationRangeStart, long sourceRangeStart, long rangeLength) {
        ConversionMap(List<Long> map) {
            this(map.get(0), map.get(1), map.get(2));
        }
    }

    private static Long getNextLocation(Long location, List<ConversionMap> conversionMaps) {
        for (var conversionMap : conversionMaps) {
            if (location >= conversionMap.sourceRangeStart && location < conversionMap.sourceRangeStart + conversionMap.rangeLength) {
                return conversionMap.destinationRangeStart + location - conversionMap.sourceRangeStart;
            }
        }
        return location;
    }
    
    private static Long getPreviousLocation(Long location, List<ConversionMap> conversionMaps) {
        for (var conversionMap : conversionMaps) {
            if (location >= conversionMap.destinationRangeStart && location < conversionMap.destinationRangeStart + conversionMap.rangeLength) {
                return conversionMap.sourceRangeStart + location - conversionMap.destinationRangeStart;
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
