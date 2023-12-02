package org.anne.aoc2023;

import org.anne.common.Day;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day2 extends Day {
    public static void main(String[] args) {
        Day day = new Day2();
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }


    public static int part1(List<String> input) {
        int maxRed = 12, maxGreen = 13, maxBlue = 14;
        int result = 0;
        for (String line : input) {
            String[] split = line.split(":");
            String[] parts = split[1].split(";");
            
            int game = Integer.parseInt(split[0].split(" ")[1]);
            boolean valid = true;
            for (String part : parts) {
                if (valid) {
                    int red = 0, green = 0, blue = 0;
                    String[] cubes = part.split(",");
                    for (String cube : cubes) {
                        String[] cubeParts = cube.trim().split(" ");
                        int count = Integer.parseInt(cubeParts[0]);
                        String color = cubeParts[1];
                        switch (color) {
                            case "red" -> red = count;
                            case "green" -> green = count;
                            case "blue" -> blue = count;
                        }
                        if (red > maxRed || green > maxGreen || blue > maxBlue) {
                            valid = false;
                            break;
                        }
                    }
                }
            }
            if (valid) {
                result += game;
            }
        }
        return result;
    }

    public static long part2(List<String> input) {
        int result = 0;
        for (String line : input) {
            int maxRed = 0, maxGreen = 0, maxBlue = 0;
            String[] split = line.split(":");
            String[] parts = split[1].split(";");
            for (String part : parts) {
                int red = 0, green = 0, blue = 0;
                String[] cubes = part.split(",");
                for (String cube : cubes) {
                    String[] cubeParts = cube.trim().split(" ");
                    int count = Integer.parseInt(cubeParts[0]);
                    String color = cubeParts[1];
                    switch (color) {
                        case "red" -> red = count;
                        case "green" -> green = count;
                        case "blue" -> blue = count;
                    }
                }
                maxRed = Math.max(maxRed, red);
                maxGreen = Math.max(maxGreen, green);
                maxBlue = Math.max(maxBlue, blue);
            }
            
            result += maxRed * maxGreen * maxBlue;
        }
        return result;
    }
}
