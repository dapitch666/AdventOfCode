package org.anne.aoc2020;

import org.anne.common.Day;
import org.anne.common.GridHelper;

import java.awt.*;
import java.util.List;

public class Day11 extends Day {

    public static void main(String[] args) {
        Day day = new Day11();
        day.setName("Seating System");
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    static int part1(List<String> input) {
        char[][] waitingArea = GridHelper.getCharGrid(input);
        return countSeats(waitingArea, true, 4);
    }

    static int part2(List<String> input) {
        char[][] waitingArea = GridHelper.getCharGrid(input);
        return countSeats(waitingArea, false, 5);
    }

    private static int countSeats(char[][] waitingArea, boolean adjacent, int maxSeat) {
        int count = 0;
        boolean hasWaitingAreaChanged = true;
        while (hasWaitingAreaChanged) {
            hasWaitingAreaChanged = false;
            count = 0;
            char[][] waitingAreaCopy = new char[waitingArea.length][waitingArea[0].length];
            for (int i = 0; i < waitingArea.length; i++) {
                for (int j = 0; j < waitingArea[i].length; j++) {
                    char currentSeat = waitingArea[i][j];
                    if (currentSeat == '.') {
                        waitingAreaCopy[i][j] = currentSeat;
                    } else {
                        int adjacentSeatsCount;
                        if (adjacent) {
                            adjacentSeatsCount = countOccupiedAdjacentSeats(waitingArea, i, j);
                        } else {
                            adjacentSeatsCount = countOccupiedSeats(waitingArea, i, j);
                        }
                        if (currentSeat == 'L') {
                            if (adjacentSeatsCount == 0) {
                                waitingAreaCopy[i][j] = '#';
                                count++;
                                hasWaitingAreaChanged = true;
                            } else {
                                waitingAreaCopy[i][j] = 'L';
                            }
                        } else if (currentSeat == '#') {
                            if (adjacentSeatsCount > maxSeat) {
                                waitingAreaCopy[i][j] = 'L';
                                hasWaitingAreaChanged = true;
                            } else {
                                waitingAreaCopy[i][j] = '#';
                                count++;
                            }
                        }
                    }
                }
            }
            waitingArea = waitingAreaCopy;
        }
        return count;
    }

    private static int countOccupiedAdjacentSeats(char[][] waitingArea, int i, int j) {
        int count = 0;
        for (int y = -1; y <= 1; y++) {
            for (int x = -1; x <= 1; x++) {
                Point point = new Point(j + x, i + y);
                if (GridHelper.isValidPoint(point, waitingArea)) {
                    char s = waitingArea[point.y][point.x];
                    if (s == '#') {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static int countOccupiedSeats(char[][] waitingArea, int i, int j) {
        int count = 0;
        for (int y = -1; y <= 1; y++) {
            for (int x = -1; x <= 1; x++) {
                boolean isFloor = true;
                int mult = 1;
                while (isFloor) {
                    Point point = new Point(j + mult * x, i + mult * y);
                    if (GridHelper.isValidPoint(point, waitingArea)) {
                        char s = waitingArea[point.y][point.x];
                        if (s == 'L') {
                            isFloor = false;
                        }
                        if (s == '#') {
                            count++;
                            isFloor = false;
                        }
                    } else {
                        isFloor = false;
                    }
                    mult++;
                }
            }
        }
        return count;
    }
}
