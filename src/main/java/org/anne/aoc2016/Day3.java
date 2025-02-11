package org.anne.aoc2016;

import org.anne.common.Day;

import java.util.List;

public class Day3 extends Day {
    public static void main(String[] args) {
        new Day3().run();
    }

    @Override
    public void execute() {
        setName("Squares With Three Sides");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(List<String> input) {
        return (int) input.stream()
                .map(line -> line.strip().split("\\s+"))
                .filter(Day3::isValidTriangle)
                .count();
    }

    public static int part2(List<String> input) {
        int count = 0;
        for (int i = 0; i < input.size(); i += 3) {
            String[] first = input.get(i).strip().split("\\s+");
            String[] second = input.get(i+1).strip().split("\\s+");
            String[] third = input.get(i+2).strip().split("\\s+");
            for (int j = 0; j < 3; j++) {
                if (isValidTriangle(new String[] {first[j], second[j], third[j]})) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isValidTriangle(String[] strings) {
        int a = Integer.parseInt(strings[0]);
        int b = Integer.parseInt(strings[1]);
        int c = Integer.parseInt(strings[2]);
        return (a + b > c && b + c > a && a + c > b);
    }
}
