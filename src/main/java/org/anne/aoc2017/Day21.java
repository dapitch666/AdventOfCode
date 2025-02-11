package org.anne.aoc2017;

import org.anne.common.Day;

import java.util.*;

public class Day21 extends Day {
    public static void main(String[] args) {
        new Day21().run();
    }

    @Override
    public void execute() {
        setName("Fractal Art");
        List<String> input = readFile();
        setPart1(part1(input, 5));
        setPart2(part2(input));
    }

    public static int part1(List<String> input, int times) {
        return getResult(input, times);
    }

    public static int part2(List<String> input) {
        return getResult(input, 18);
    }

    private static int getResult(List<String> input, int times) {
        String grid = ".#...####";
        Map<String, String> rules = new HashMap<>();
        for (String s : input) {
            String[] parts = s.replace("/", "").split(" => ");
            for (String pattern : patternList(parts[0])) {
                rules.put(pattern, parts[1]);
            }
        }

        for (int i = 0; i < times; i++) {
            int size = (grid.length() % 2 == 0) ? 2 : 3;
            List<String> parts = split(grid, size).stream()
                    .map(rules::get)
                    .toList();
            grid = join(parts, size + 1);
        }
        return (int) grid.chars().filter(c -> c == '#').count();
    }

    static List<String> split(String grid, int squareSize) {
        int size = (int) Math.sqrt(grid.length());
        List<String> parts = new ArrayList<>();
        for (int row = 0; row < size; row += squareSize) {
            for (int col = 0; col < size; col += squareSize) {
                StringBuilder sb = new StringBuilder(squareSize * squareSize);
                for (int y = 0; y < squareSize; y++) {
                    sb.append(grid, (row + y) * size + col, (row + y) * size + col + squareSize);
                }
                parts.add(sb.toString());
            }
        }
        return parts;
    }

    static String join(List<String> parts, int squareSize) {
        int size = (int) Math.sqrt(parts.size()) * squareSize;
        char[] grid = new char[size * size];
        int index = 0;
        for (int row = 0; row < size; row += squareSize) {
            for (int col = 0; col < size; col += squareSize) {
                char[] part = parts.get(index++).toCharArray();
                for (int y = 0; y < squareSize; y++) {
                    System.arraycopy(part, y * squareSize, grid, (row + y) * size + col, squareSize);
                }
            }
        }
        return new String(grid);
    }

    static Set<String> patternList(String grid) {
        Set<String> patternList = new HashSet<>(List.of(grid, flip(grid)));
        for (int i = 0; i < 3; i++) {
            grid = rotate90(grid);
            patternList.addAll(List.of(grid, flip(grid)));
        }
        return patternList;
    }

    static String rotate90(String grid) {
        int size = (int) Math.sqrt(grid.length());
        StringBuilder res = new StringBuilder(grid.length());
        for (int x = 0; x < size; x++) {
            for (int y = size - 1; y >= 0; y--) {
                res.append(grid.charAt(y * size + x));
            }
        }
        return res.toString();
    }

    static String flip(String grid) {
        int size = (int) Math.sqrt(grid.length());
        StringBuilder res = new StringBuilder(grid.length());
        for (int y = 0; y < size; y++) {
            for (int x = size - 1; x >= 0; x--) {
                res.append(grid.charAt(y * size + x));
            }
        }
        return res.toString();
    }
}
