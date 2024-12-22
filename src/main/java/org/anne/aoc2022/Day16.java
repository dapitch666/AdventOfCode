package org.anne.aoc2022;

import org.anne.common.Day;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day16 extends Day {
    public static void main(String[] args) {
        new Day16().run();
    }

    @Override
    public void execute() {
        setName("Proboscidea Volcanium");
        List<String> input = readFile();

        var valves = parseInput(input);
        var paths = getPaths(valves);
        var maxPressureGrid = getAllPressures(valves, paths);

        setPart1(part1(maxPressureGrid));
        setPart2(part2(maxPressureGrid));
        printParts();
    }

    public static long part1(int[][][] dp) {
        return bestPressure(dp, false);
    }

    public static int part2(int[][][] dp) {
        return bestPressure(dp, true);
    }

    public record Valve (String name, int flowRate, List<String> tunnels) {}

    public static int getFlowOfBitmask(List<Valve> nonzero, int bitmask) {
        int flow = 0;
        for (int i = 0; i < nonzero.size(); i++) {
            if (isOpen(i, bitmask)) {
                flow += nonzero.get(i).flowRate;
            }
        }
        return flow;
    }

    public static boolean isOpen (int index, int bitmask) {
        return (((1 << index) & bitmask) != 0);
    }

    static List<Valve> parseInput(List<String> input) {
        List<Valve> valves = new ArrayList<>();
        Pattern pattern = Pattern.compile("^Valve ([A-Z]{2}) has flow rate=(\\d+); [a-z ]+ ([A-Z, ]+)$");
        for (String line : input) {
            Matcher m = pattern.matcher(line);
            if (m.matches()) {
                valves.add(new Valve(m.group(1), Integer.parseInt(m.group(2)), Arrays.stream(m.group(3).split(", ")).toList()));
            }
        }
        return valves;
    }

    static Map<String, Map<String, Integer>> getPaths(List<Valve> valves) {
        Map<String, Map<String, Integer>> paths = new HashMap<>();
        for(Valve v : valves) {
            Deque<Valve> queue = new LinkedList<>();
            queue.add(v);
            Map<String, Integer> distances = new HashMap<>();
            distances.put(v.name, 0);
            Set<String> seen = new HashSet<>();
            seen.add(v.name);

            while(!queue.isEmpty()) {
                Valve cur = queue.poll();
                int distFrom = distances.get(cur.name);

                for(String connection : cur.tunnels) {
                    if(!seen.contains(connection)) {
                        seen.add(connection);
                        distances.put(connection, distFrom + 1);
                        queue.add(valves.stream().filter(x -> x.name.equals(connection)).findFirst().orElseThrow());
                    }
                }
            }
            paths.put(v.name, distances);
        }
        return paths;
    }

    static int[][][] getAllPressures(List<Valve> valves, Map<String, Map<String, Integer>> paths) {
        var nonzeroFlow = new ArrayList<>(valves.stream().filter(x -> x.flowRate > 0).toList());
        var maxValves = nonzeroFlow.size();
        int maxBitmask = 1 << maxValves;
        int[][][] maxPressureGrid = new int[31][maxValves][maxBitmask];
        for(int[][] square : maxPressureGrid) {
            for (int[] row : square) {
                Arrays.fill(row, Integer.MIN_VALUE);
            }
        }
        for(int i = 0; i < maxValves; i++) {
            maxPressureGrid[paths.get("AA").get(nonzeroFlow.get(i).name) + 1][i][1 << i] = 0;
        }

        for(int minute = 1; minute < 31; minute++) {
            for(int curPos = 0; curPos < maxValves; curPos++) {
                for(int bitset = 0; bitset < maxBitmask; bitset++) {
                    int potentialFlow = getFlowOfBitmask(nonzeroFlow,bitset);
                    int newPressure = maxPressureGrid[minute-1][curPos][bitset] + potentialFlow;
                    if(newPressure > maxPressureGrid[minute][curPos][bitset]) {
                        maxPressureGrid[minute][curPos][bitset] = newPressure;
                    }
                    if(!isOpen(curPos, bitset)) {
                        continue;
                    }
                    for(int other = 0; other < maxValves; other++) {
                        if(isOpen(other, bitset)) {
                            continue;
                        }
                        int distTo = paths.get(nonzeroFlow.get(curPos).name).get(nonzeroFlow.get(other).name);
                        if(minute + distTo + 1 > 30) {
                            continue;
                        }
                        int travelPressure = maxPressureGrid[minute][curPos][bitset] + potentialFlow * (distTo + 1);
                        int newBitset = bitset | (1 << other);
                        if(travelPressure > maxPressureGrid[minute + distTo + 1][other][newBitset]) {
                            maxPressureGrid[minute + distTo + 1][other][newBitset] = travelPressure;
                        }
                    }
                }
            }
        }
        return maxPressureGrid;
    }

    private static int bestPressure(int[][][] maxPressureGrid, boolean isPart2) {
        int bestPressure = 0;
        int maxMinutes = isPart2 ? 26 : 30;
        int maxBitmask = maxPressureGrid[0][0].length;
        int maxValves = maxPressureGrid[0].length;
        if (isPart2) {
            for (int firstBitMask = 1; firstBitMask < maxBitmask; firstBitMask++) {
                for (int secondBitMask = 1; secondBitMask < maxBitmask; secondBitMask++) {
                    if ((firstBitMask & secondBitMask) != secondBitMask) continue;
                    int me = 0;
                    int elephant = 0;

                    for (int i = 0; i < maxValves; i++) {
                        me = Math.max(me, maxPressureGrid[maxMinutes][i][(firstBitMask & (~secondBitMask))]);
                        elephant = Math.max(elephant, maxPressureGrid[maxMinutes][i][secondBitMask]);
                    }

                    bestPressure = Math.max(bestPressure, me + elephant);
                }
            }
            return bestPressure;
        } else {
            return Arrays.stream(maxPressureGrid[maxMinutes])
                    .flatMapToInt(Arrays::stream)
                    .max()
                    .orElseThrow();
        }
    }
}
