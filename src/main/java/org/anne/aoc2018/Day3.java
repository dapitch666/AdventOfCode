package org.anne.aoc2018;

import org.anne.common.Day;

import java.util.*;

public class Day3 extends Day {
    public static void main(String[] args) {
        new Day3().run();
    }

    @Override
    public void execute() {
        setName("No Matter How You Slice It");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(List<String> input) {
        int[][] fabric = new int[1000][1000];
        for (String line : input) {
            Claim claim = Claim.fromString(line);
            for (int y = claim.y; y < claim.y + claim.h; y++) {
                for (int x = claim.x; x < claim.x + claim.w; x++) {
                    fabric[y][x]++;
                }
            }
        }
        return Arrays.stream(fabric)
                .mapToInt(row -> (int) Arrays.stream(row).filter(i -> i > 1).count())
                .sum();
    }

    public static int part2(List<String> input) {
        String[][] fabric = new String[1000][1000];
        Set<Integer> candidates = new HashSet<>();
        for (String line : input) {
            Claim claim = Claim.fromString(line);
            boolean overlap = false;
            for (int y = claim.y; y < claim.y + claim.h; y++) {
                for (int x = claim.x; x < claim.x + claim.w; x++) {
                    String value = fabric[y][x];
                    if (value != null) {
                        for (String s : value.split(",")) {
                            candidates.remove(Integer.parseInt(s));
                        }
                        overlap = true;
                        fabric[y][x] = value + "," + claim.id;
                    } else {
                        fabric[y][x] = String.valueOf(claim.id);
                    }
                }
            }
            if (!overlap) {
                candidates.add(claim.id);
            }
        }

        return candidates.size() == 1 ? candidates.iterator().next() : -1;
    }

    record Claim(int id, int x, int y, int w, int h) {
        static Claim fromString(String s) {
            String[] parts = s.split("[@,:x]");
            return new Claim(Integer.parseInt(parts[0].replace("#", "").trim()),
                    Integer.parseInt(parts[1].trim()),
                    Integer.parseInt(parts[2].trim()),
                    Integer.parseInt(parts[3].trim()),
                    Integer.parseInt(parts[4].trim()));

        }
    }
}
