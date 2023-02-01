package org.anne.aoc2021;

import org.anne.common.Day;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day9 extends Day {

    public static void main(String[] args) {
        Day day = new Day9();
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    public static int part1(List<String> input) {
        int[][] caveMap = drawMap(input);
        int sum = 0;
        List<Point> lowPoints = getLowPoints(caveMap);
        for (Point lowPoint : lowPoints) {
            sum += caveMap[lowPoint.x][lowPoint.y] + 1;
        }
        return sum;
    }

    public static long part2(List<String> input) {
        int[][] caveMap = drawMap(input);
        List<Point> lowPoints = getLowPoints(caveMap);
        List<Set<Point>> basins = new ArrayList<>();
        for (Point lowPoint : lowPoints) {
            Set<Point> basin = new HashSet<>();
            getBasin(lowPoint, caveMap, basin);
            basins.add(basin);
        }
        basins.sort((a1, a2) -> a2.size() - a1.size());
        return (long) basins.get(0).size() * basins.get(1).size() * basins.get(2).size();
    }

    private static void getBasin(Point point, int[][] caveMap, Set<Point> basin) {
        int x = point.x;
        int y = point.y;
        int height = caveMap[x][y];
        if (height == 9 || basin.contains(point)) {
            return;
        }
        basin.add(point);
        if (x > 0) {
            getBasin(new Point(x - 1, y), caveMap, basin);
        }
        if (x < caveMap.length - 1) {
            getBasin(new Point(x + 1, y), caveMap, basin);
        }
        if (y > 0) {
            getBasin(new Point(x, y - 1), caveMap, basin);
        }
        if (y < caveMap[0].length - 1) {
            getBasin(new Point(x, y + 1), caveMap, basin);
        }
    }

    private static int[][] drawMap(List<String> input) {
        int vSize = input.size();
        int hSize = input.get(0).length();
        int[][] caveMap = new int[vSize][hSize];
        for (int i = 0; i < vSize; i++) {
            for (int j = 0; j < hSize; j++) {
                caveMap[i][j] = Integer.parseInt(String.valueOf(input.get(i).charAt(j)));
            }
        }
        return caveMap;
    }

    private static List<Point> getLowPoints(int[][] caveMap) {
        List<Point> lowPoints = new ArrayList<>();
        for (int i = 0; i < caveMap.length; i++) {
            for (int j = 0; j < caveMap[0].length; j++) {
                if (isMin(caveMap, i, j)) {
                    lowPoints.add(new Point(i, j));
                }
            }
        }
        return lowPoints;
    }

    private static boolean isMin(int[][] caveMap, int x, int y) {
        int height = caveMap[x][y];
        if (height == 0) {
            return true;
        } else if (height == 9) {
            return false;
        } else {
            if (x > 0 && caveMap[x - 1][y] < height) {
                return false;
            }
            if (x < caveMap.length - 1 && caveMap[x + 1][y] < height) {
                return false;
            }
            if (y > 0 && caveMap[x][y - 1] < height) {
                return false;
            }
            return y >= caveMap[0].length - 1 || caveMap[x][y + 1] >= height;
        }
    }
}
