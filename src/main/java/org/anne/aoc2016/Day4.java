package org.anne.aoc2016;

import org.anne.common.Day;

import java.util.*;
import java.util.stream.Collectors;


public class Day4 extends Day {
    public static void main(String[] args) {
        new Day4().run();
    }

    @Override
    public void execute() {
        setName("Security Through Obscurity");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(List<String> input) {
        return input.stream()
                .map(Room::fromString)
                .filter(Room::isValid)
                .mapToInt(Room::sectorId)
                .sum();
    }

    public static int part2(List<String> input) {
        return input.stream()
                .map(Room::fromString)
                .filter(r -> r.isValid() && r.decrypt().contains("northpole"))
                .mapToInt(Room::sectorId)
                .findFirst().orElseThrow();
    }

    record Room(String name, int sectorId, String checksum) {
        static Room fromString(String s) {
            String[] parts = s.split("-");
            String[] lastPart = parts[parts.length - 1].split("\\[");
            String name = String.join("-", Arrays.copyOf(parts, parts.length - 1));
            int sectorId = Integer.parseInt(lastPart[0]);
            String checksum = lastPart[1].replace("]", "");
            return new Room(name, sectorId, checksum);
        }

        boolean isValid() {
            Map<Character, Integer> charMap = new HashMap<>();
            for (char c : name.replace("-", "").toCharArray()) {
                charMap.put(c, charMap.getOrDefault(c, 0) + 1);
            }
            String computedChecksum = charMap.entrySet().stream()
                    .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed()
                            .thenComparing(Map.Entry::getKey))
                    .map(Map.Entry::getKey)
                    .map(Object::toString)
                    .collect(Collectors.joining());
            return computedChecksum.substring(0, 5).equals(checksum);
        }

        String decrypt() {
            StringBuilder stringBuilder = new StringBuilder();
            for (char c : name.toCharArray()) {
                if (c == '-') {
                    stringBuilder.append(" ");
                } else {
                    stringBuilder.append((char) ((c - 'a' + sectorId) % 26 + 'a'));
                }
            }
            return stringBuilder.toString();
        }
    }
}
