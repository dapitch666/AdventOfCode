package org.anne.aoc2021;

import org.anne.common.Day;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day11 extends Day {
    static Octopus[][] grid;

    public static void main(String[] args) {
        Day day = new Day11();
        day.setName("Dumbo Octopus");
        List<String> input = day.readFile();
        day.setPart1(part1(input, 100));
        day.setPart2(part2(input));
        day.printParts();
    }

    public static int part1(List<String> input, int steps) {
        int gridSize = input.size();
        grid = new Octopus[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            for(int j = 0; j < gridSize; j++) {
                grid[i][j] = new Octopus(Integer.parseInt(String.valueOf(input.get(i).charAt(j))));
            }
        }

       for (int t = 0; t < steps; t++) {
           step();
       }

        return Arrays.stream(grid)
                .flatMapToInt(octopi -> Arrays.stream(octopi).flatMapToInt(octopus -> IntStream.of(octopus.getFlashes())))
                .sum();
    }

    public static long part2(List<String> input) {
        int gridSize = input.size();
        grid = new Octopus[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            for(int j = 0; j < gridSize; j++) {
                grid[i][j] = new Octopus(Integer.parseInt(String.valueOf(input.get(i).charAt(j))));
            }
        }
        int counter = 0;
        while (!allFlashed()) {
            step();
            counter++;
        }
        return counter;
    }

    public static void step() {
        while (hasNines()) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j].energy == 9) {
                        flash(i, j);
                    }
                }
            }
        }
        for (Octopus[] octopi : grid) {
            for (int j = 0; j < grid[0].length; j++) {
                Octopus octopus = octopi[j];
                if (octopus.energy == 10) {
                    octopus.energy = 0;
                    octopus.flashes++;
                } else {
                    octopus.energy++;
                }
            }
        }
    }

    private static boolean hasNines() {
        for (Octopus[] octopi : grid) {
            for (int j = 0; j < grid.length; j++) {
                if (octopi[j].energy == 9) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void flash(int i, int j) {
        for (int di = Math.max(i - 1, 0); di < grid.length && di <= i + 1; di++) {
            for (int dj = Math.max(j - 1, 0); dj < grid.length && dj <= j + 1; dj++) {
                if (grid[di][dj].energy < 9) {
                    grid[di][dj].energy++;
                }
            }
        }
        grid[i][j].energy = 10;
    }

    private static boolean allFlashed() {
        for (Octopus[] octopi : grid) {
            for (int j = 0; j < grid.length; j++) {
                if (octopi[j].energy != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static class Octopus {
        int energy;
        int flashes = 0;

        public Octopus(int energy) {
            this.energy = energy;
        }

        public int getFlashes() {
            return this.flashes;
        }

        @Override
        public String toString() {
            return String.valueOf(this.energy);
        }
    }

}
