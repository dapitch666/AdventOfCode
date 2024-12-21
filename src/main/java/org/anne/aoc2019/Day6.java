package org.anne.aoc2019;


import org.anne.common.Day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day6 extends Day {
    private static Map<String, String> orbits = new HashMap<>();

    public static void main(String[] args) {
        Day day = new Day6();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Universal Orbit Map");
        List<String> input = this.readFile();
        this.setPart1(part1(input));
        this.setPart2(part2(input));
        this.printParts();
    }

    static int part1(List<String> input) {
        orbits = input.stream()
                .collect(Collectors.toMap(s -> s.split("\\)")[1], s -> s.split("\\)")[0]));
        int counter = 0;
        for (String key : orbits.keySet()) {
            counter += getNbOrbits(key);
        }
        return counter;
    }

    static int part2(List<String> input) {
        orbits = input.stream()
                .collect(Collectors.toMap(s -> s.split("\\)")[1], s -> s.split("\\)")[0]));
        String pivotObject = getPivotObject();
        int myNbOrbits = getNbOrbits("YOU");
        int santaNbOrbits = getNbOrbits("SAN");
        int pivotNbOrbits = getNbOrbits(pivotObject);
        return myNbOrbits + santaNbOrbits - 2 - 2 * pivotNbOrbits;
    }

    private static int getNbOrbits(String obj) {
        int count = 0;
        if (!orbits.containsKey(obj)) {
            return 0;
        }
        count += 1 + getNbOrbits(orbits.get(obj));
        return count;
    }

    private static String getPivotObject() {
        String currentKey = "YOU";
        List<String> path = new ArrayList<>();
        while (orbits.containsKey(currentKey)) {
            currentKey = orbits.get(currentKey);
            path.add(currentKey);
        }
        currentKey = "SAN";
        while (orbits.containsKey(currentKey) && !path.contains(currentKey)) {
            currentKey = orbits.get(currentKey);
        }
        return currentKey;
    }
}
