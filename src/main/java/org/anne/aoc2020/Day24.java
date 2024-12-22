package org.anne.aoc2020;

import org.anne.common.Day;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Day24 extends Day {

    private static final int DAYS = 100;

    public static void main(String[] args) {
        new Day24().run();
    }

    @Override
    public void execute() {
        setName("Lobby Layout");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(List<String> input) {
        int[][] floor = getFloor(input);
        return Arrays.stream(floor).flatMapToInt(Arrays::stream).sum();
    }

    public static int part2(List<String> input) {
        int[][] floor = getFloor(input);
        int size = floor.length;
        for (int i = 0; i < DAYS; i++) {
            int[][] floorCopy = new int[size][size];
            for (int x = 0; x < size; x++) {
                for (int y = (x%2 == 0) ? 0 : 1 ; y < size; y += 2) {
                    int activeNeighbors = countActiveNeighbors(floor, x, y);
                    if (floor[x][y] == 1) {
                        if (activeNeighbors == 1 || activeNeighbors == 2) {
                            floorCopy[x][y] = 1;
                        }
                    } else if (activeNeighbors == 2) {
                        floorCopy[x][y] = 1;
                    }
                }
            }
            floor = floorCopy;
        }
        return Arrays.stream(floor).flatMapToInt(Arrays::stream).sum();
    }

    private static int[][] getFloor(List<String> input) {
        Map<String, Integer> floorMap = new HashMap<>();
        for (String line : input) {
            int x = 0;
            int y = 0;
            boolean prev = false;
            for (char dir : line.toCharArray()) {
                if (dir == 'e') {
                    if (prev) {
                        x += 1;
                    } else {
                        x += 2;
                    }
                    prev = false;
                } else if (dir == 'w') {
                    if (prev) {
                        x -= 1;
                    } else {
                        x -= 2;
                    }
                    prev = false;
                } else {
                    prev = true;
                    if (dir == 'n') {
                        y += 1;
                    } else {
                        y -= 1;
                    }
                }
            }
            HexTile tile = new HexTile(x, y);
            int count = floorMap.getOrDefault(tile.toString(), 0);
            floorMap.put(tile.toString(), count + 1);
        }
        int size = floorMap.keySet().stream().mapToInt(s -> Integer.parseInt(s.split(", ")[0].replaceAll("x: ", ""))).max().orElse(-1) + 1 + DAYS;

        int[][] floor = new int[size * 2][size * 2];
        for (Map.Entry<String, Integer> entry : floorMap.entrySet()) {
            HexTile tile = new HexTile(entry.getKey());
            if (entry.getValue() % 2 != 0) {
                floor[size + tile.x][size + tile.y] = 1;
            }
        }
        return floor;
    }

    private static int countActiveNeighbors(int[][] floor, int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                try {
                    count += floor[x+i][y+j];
                } catch (ArrayIndexOutOfBoundsException e) {
                    // Do nothing
                }
            }
        }
        try {
            count += floor[x - 2][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            // Do nothing
        }
        try {
            count += floor[x + 2][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            // Do nothing
        }
        return count;
    }


    private record HexTile (int x, int y) {
        private HexTile(String str) {
            this(Integer.parseInt(str.split(", ")[0].replaceAll("x: ", "")), Integer.parseInt(str.split(", ")[1].replaceAll("y: ", "")));
        }

        @Override
        public String toString() {
            return "x: " + x + ", y: " + y;
        }
    }
}
