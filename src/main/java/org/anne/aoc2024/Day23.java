package org.anne.aoc2024;

import org.anne.common.Day;

import java.util.*;
import java.util.stream.Collectors;

public class Day23 extends Day {
    public static void main(String[] args) {
        new Day23().run();
    }

    @Override
    public void execute() {
        setName("LAN Party");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static long part1(List<String> input) {
        return getLanSet(getNetworkMap(input)).stream()
                .filter(set -> set.stream().anyMatch(s -> s.startsWith("t")))
                .count();
    }

    public static String part2(List<String> input) {
        Map<String, Set<String>> networkMap = getNetworkMap(input);
        Set<Set<String>> lanSet = getLanSet(networkMap);

        for (String computer : networkMap.keySet()) {
            Set<String> connections = networkMap.get(computer);
            for (Set<String> lan : lanSet) {
                if (lan.contains(computer)) {
                    for (String connection : connections) {
                        if (!lan.contains(connection) && networkMap.get(connection).containsAll(lan)) {
                            lan.add(connection);
                        }
                    }
                }
            }
        }
        return lanSet.stream()
                .max(Comparator.comparingInt(Set::size))
                .orElse(Collections.emptySet())
                .stream()
                .sorted()
                .collect(Collectors.joining(","));
    }

    private static Map<String, Set<String>> getNetworkMap(List<String> input) {
        Map<String, Set<String>> networkMap = new HashMap<>();
        for (String line : input) {
            String[] connection = line.split("-");
            networkMap.computeIfAbsent(connection[0], k -> new HashSet<>()).add(connection[1]);
            networkMap.computeIfAbsent(connection[1], k -> new HashSet<>()).add(connection[0]);
        }
        return networkMap;
    }

    private static Set<Set<String>> getLanSet(Map<String, Set<String>> map) {
        Set<Set<String>> lanSet = new HashSet<>();
        for (String computer1 : map.keySet()) {
            Set<String> connections = map.get(computer1);
            for (String computer2 : connections) {
                for (String computer3 : map.get(computer2)) {
                    if (connections.contains(computer3)) {
                        lanSet.add(new HashSet<>(List.of(computer1, computer2, computer3)));
                    }
                }
            }
        }
        return lanSet;
    }
}