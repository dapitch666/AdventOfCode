package org.anne.aoc2020;

import org.anne.common.Day;

import java.util.*;


public class Day17 extends Day {

    private static final int TURNS = 6;

    public static void main(String[] args) {
        Day day = new Day17();
        day.setName("Conway Cubes");
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    public static int part1(List<String> input) {
        int size = input.get(0).length();
        int finalSize = size + 2 * TURNS;

        int[][][] cubes = newCube(finalSize);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (input.get(i).charAt(j) == '#') {
                    cubes[TURNS][i + TURNS][j + TURNS] = 1;
                }
            }
        }

        for (int i = 0; i < TURNS; i++) {
            int[][][] cubesCopy = newCube(finalSize);
            for (int x = 0; x < finalSize; x++) {
                for (int y = 0; y < finalSize; y++) {
                    for (int z = 0; z < finalSize; z++) {
                        int activeNeighbors = countActiveNeighbors(cubes, x, y, z);
                        if (cubes[x][y][z] == 1) {
                            if (activeNeighbors == 2 || activeNeighbors == 3) {
                                cubesCopy[x][y][z] = 1;
                            }
                        } else {
                            if (activeNeighbors == 3) {
                                cubesCopy[x][y][z] = 1;
                            }
                        }
                    }
                }
            }
            cubes = cubesCopy;
        }

        return Arrays.stream(cubes)
                .flatMap(Arrays::stream)
                .flatMapToInt(Arrays::stream)
                .sum();
    }

    public static int part2(List<String> input) {
        int size = input.get(0).length();
        int finalSize = size + 2 * TURNS;
        int[][][][] cubes = newCubePart2(finalSize);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (input.get(i).charAt(j) == '#') {
                    cubes[TURNS][TURNS][i + TURNS][j + TURNS] = 1;
                }
            }
        }

        for (int i = 0; i < TURNS; i++) {
            int[][][][] cubesCopy = newCubePart2(finalSize);
            for (int x = 0; x < finalSize; x++) {
                for (int y = 0; y < finalSize; y++) {
                    for (int z = 0; z < finalSize; z++) {
                        for (int w = 0; w < finalSize; w++) {
                            int activeNeighbors = countActiveNeighbors(cubes, x, y, z, w);
                            if (cubes[x][y][z][w] == 1) {
                                if (activeNeighbors == 2 || activeNeighbors == 3) {
                                    cubesCopy[x][y][z][w] = 1;
                                }
                            } else {
                                if (activeNeighbors == 3) {
                                    cubesCopy[x][y][z][w] = 1;
                                }
                            }
                        }
                    }
                }
            }
            cubes = cubesCopy;
        }

        return Arrays.stream(cubes)
                .flatMap(Arrays::stream)
                .flatMap(Arrays::stream)
                .flatMapToInt(Arrays::stream)
                .sum();
    }

    private static int countActiveNeighbors(int[][][][] cube, int x, int y, int z, int w) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                for (int k = -1; k <= 1; k++) {
                    for (int l = -1; l <= 1; l++) {
                        if (i == 0 && j == 0 && k == 0 && l == 0) {
                            continue;
                        }
                        try {
                            count += cube[x + i][y + j][z + k][w + l];
                        } catch (ArrayIndexOutOfBoundsException e) {
                            // Do nothing
                        }
                    }
                }
            }
        }
        return count;
    }

    private static int countActiveNeighbors(int[][][] cube, int x, int y, int z) {
        int count = 0;
        for (int i = -1; i <= 1; i++) { // Layer
            for (int j = -1; j <= 1; j++) { // Row
                for (int k = -1; k <= 1; k++) { // Char
                    if (i == 0 && j == 0 && k == 0) {
                        continue;
                    }
                    try {
                        count += cube[x+i][y+j][z+k];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        // Do nothing
                    }
                }
            }
        }
        return count;
    }

    private static int[][][] newCube(int size) {
        int[][][] cube = new int[size][size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    cube[i][j][k] = 0;
                }
            }
        }
        return cube;
    }

    private static int[][][][] newCubePart2(int size) {
        int[][][][] cube = new int[size][size][size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    for (int l = 0; l < size; l++) {
                        cube[i][j][k][l] = 0;
                    }
                }
            }
        }
        return cube;
    }
}
