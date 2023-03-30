package org.anne.aoc2020;

import org.anne.common.Day;

import java.util.List;

public class Day12 extends Day {
    private static final char[] cardinal = {'N', 'E', 'S', 'W'};

    public static void main(String[] args) {
        Day day = new Day12();
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    static int part1(List<String> input) {
        int x = 0, y = 0;
        char facing = 'E';
        for (String s : input) {
            char instruction = s.charAt(0);
            if (instruction == 'R' || instruction == 'L') {
                facing = rotate(facing, instruction, Integer.parseInt(s.substring(1)));
            } else {
                if (instruction == 'F') {
                    instruction = facing;
                }
                if (isCardinal(instruction)) {
                    int[] direction = getDir(instruction);
                    int distance = Integer.parseInt(s.substring(1));
                    x = x + distance * direction[0];
                    y = y + distance * direction[1];
                }
            }
        }
        return Math.abs(x) + Math.abs(y);
    }

    static int part2(List<String> input) {
        int x = 0, y = 0;
        int waypointX = 10, waypointY = 1;
        for (String s : input) {
            char instruction = s.charAt(0);
            if (instruction == 'R' || instruction == 'L') {
                int degree = Integer.parseInt(s.substring(1));
                int nbRotation = instruction == 'R' ? degree / 90 : (360 - degree) / 90;
                int newX = 0, newY = 0;
                switch (nbRotation) {
                    case 1 -> {
                        //noinspection SuspiciousNameCombination
                        newX = waypointY;
                        newY = -waypointX;
                    }
                    case 2 -> {
                        newX = -waypointX;
                        newY = -waypointY;
                    }
                    case 3 -> {
                        newX = -waypointY;
                        //noinspection SuspiciousNameCombination
                        newY = waypointX;
                    }
                }
                waypointX = newX;
                waypointY = newY;
            } else if (instruction == 'F') {
                int multiplier = Integer.parseInt(s.substring(1));
                x = x + multiplier * waypointX;
                y = y + multiplier * waypointY;
            } else {
                int distance = Integer.parseInt(s.substring(1));
                int[] direction = getDir(instruction);
                waypointX = waypointX + distance * direction[0];
                waypointY = waypointY + distance * direction[1];
            }
        }
        return Math.abs(x) + Math.abs(y);
    }

    private static char rotate(char facing, char instruction, int degree) {
        int nbRotation = instruction == 'R' ? degree / 90 : (360 - degree) / 90;
        int curIdx = new String(cardinal).indexOf(facing);
        if (curIdx + nbRotation < cardinal.length) {
            return cardinal[curIdx + nbRotation];
        } else {
            return cardinal[curIdx + nbRotation - cardinal.length];
        }
    }

    private static boolean isCardinal(char instruction) {
        return new String(cardinal).indexOf(instruction) >= 0;
    }

    private static int[] getDir (char c) {
        return switch (c) {
            case 'N' -> new int[]{0, 1};
            case 'S' -> new int[]{0, -1};
            case 'W' -> new int[]{-1, 0};
            case 'E' -> new int[]{1, 0};
            default -> null;
        };
    }


}
