package org.anne.aoc2020;

import org.anne.common.Day;

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
        char[][] waitingArea = input.stream().map(String::toCharArray).toArray(char[][]::new);
        return countSeats(waitingArea, true, 4);
    }

    static int part2(List<String> input) {
        char[][] waitingArea = input.stream().map(String::toCharArray).toArray(char[][]::new);
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
        for (int n = -1; n < 2; n++) {
            for (int m = -1; m < 2; m++) {
                try {
                    char s = waitingArea[i + n][j + m];
                    if (s == '#') {
                        count++;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    // Do nothing
                }
            }
        }
        return count;
    }

    private static int countOccupiedSeats(char[][] waitingArea, int i, int j) {
        int count = 0;
        for (int n = -1; n < 2; n++) {
            for (int m = -1; m < 2; m++) {
                boolean isFloor = true;
                int mult = 1;
                while (isFloor) {
                    try {
                        char s = waitingArea[i + mult * n][j + mult * m];
                        if (s == 'L') {
                            isFloor = false;
                        }
                        if (s == '#') {
                            count++;
                            isFloor = false;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        isFloor = false;
                    }
                    mult++;
                }
            }
        }
        return count;
    }
}
