package org.anne.aoc2021;

import org.anne.common.Day;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day15 extends Day {

    public static void main(String[] args) {
        Day day = new Day15();
        day.setName("Chiton");
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    public static long part1(List<String> input) {
        return getLowestTotalRisk(input);
    }

    private static long getLowestTotalRisk(List<String> input) {
        return getLowestTotalRisk(input, 1);
    }

    public static long part2(List<String> input) {
        return getLowestTotalRisk(input, 5);
    }

    private static long getLowestTotalRisk(List<String> input, int multiplier) {
        int[][] risks = input.stream()
                .map(s -> s.chars().map(Character::getNumericValue).toArray())
                .toArray(i -> new int[i][0]);

        int[][] paths = new int[risks.length * multiplier][risks[0].length * multiplier];
        for (int[] ints : paths) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }
        paths[0][0] = 0;
        int xMax = paths.length;
        int yMax = paths[0].length;

        boolean stop = false;
        do  {
            for (int i = 0; i < xMax; i++) {
                for (int j = 0; j < yMax; j++) {
                    int currentRisk = paths[i][j];
                    for (Point p : getAdjacent(i, j, xMax, yMax)) {
                        int neighbourRisk = getRisk(p, risks);
                        if (paths[p.x][p.y] <= currentRisk + neighbourRisk) {
                            stop = true;
                        } else {
                            stop = false;
                            paths[p.x][p.y] = currentRisk + neighbourRisk;
                        }
                    }
                }
            }
        } while (!stop);
        return paths[xMax - 1][yMax - 1];
    }

    private static List<Point> getAdjacent(int i, int j, int xMax, int yMax) {
        List<Point> adjacent = new ArrayList<>();
        if (i > 0) {
            adjacent.add(new Point(i - 1, j));
        }
        if (i < xMax - 1) {
            adjacent.add(new Point(i + 1, j));
        }
        if (j > 0) {
            adjacent.add(new Point(i, j - 1));
        }
        if (j < yMax - 1) {
            adjacent.add(new Point(i, j + 1));
        }
        return adjacent;
    }

    public static int getRisk(Point p, int[][] risks) {
        if (p.x < risks.length && p.y < risks[0].length) {
            return risks[p.x][p.y];
        } else {
            int risk = 0;
            if (p.y >= risks[0].length) {
                risk = getRisk(new Point(p.x, p.y - risks[0].length), risks) + 1;
            }
            if (p.x >= risks.length) {
                risk = getRisk(new Point(p.x - risks.length, p.y), risks) + 1;
            }
            return risk%10 == 0 ? 1 : risk%10;
        }
    }
}