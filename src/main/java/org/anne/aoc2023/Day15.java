package org.anne.aoc2023;

import org.anne.common.Day;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Day15 extends Day {
    public static void main(String[] args) {
        new Day15().run();
    }

    @Override
    public void execute() {
        setName("Lens Library");
        var input = readFileOneLine();
        setPart1(part1(input));
        setPart2(part2(input));
    }


    public static int part1(String input) {
        return Arrays.stream(input.split(","))
                .mapToInt(Day15::hash)
                .sum();
    }

    public static int part2(String input) {
        var hashMap = new HashMap<Integer, LinkedHashMap<String, Integer>>();
        for (var i = 0; i < 256; i++) {
            hashMap.put(i, new LinkedHashMap<>());
        }
        var steps = Arrays.stream(input.split(","))
                .toList();
        for (var step : steps) {
            var lens = step.split("[=\\-]")[0];
            var box = hash(lens);
            if (step.contains("-")) {
                hashMap.get(box).remove(lens);
            } else {
                var focalLength = Integer.parseInt(step.split("=")[1]);
                if (hashMap.get(box).containsKey(lens)) {
                    hashMap.get(box).put(lens, focalLength);
                } else {
                    var map = hashMap.get(box); 
                    map.put(lens, focalLength);
                    hashMap.put(box, map);
                }
            }
        }
        return getFocusingPower(hashMap);
    }

    private static int getFocusingPower(HashMap<Integer, LinkedHashMap<String, Integer>> hashMap) {
        var focusingPower = 0;
        for (var boxEntry : hashMap.entrySet()) {
            int box = boxEntry.getKey();
            var lenses = boxEntry.getValue();
            var index = 1;
            for (var lens : lenses.entrySet()) {
                int focalLength = lens.getValue();
                focusingPower += (box + 1) * index * focalLength;
                index++;
            }
        }
        return focusingPower;
    }

    static int hash(String str) {
        return str.chars()
                .reduce(0, (hash, ch) -> ((hash + ch) * 17) % 256);
    }
}
