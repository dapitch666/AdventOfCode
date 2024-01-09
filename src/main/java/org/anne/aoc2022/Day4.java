package org.anne.aoc2022;

import org.anne.common.Day;

import java.util.List;

public class Day4 extends Day {
    public static void main(String[] args) {
        Day day = new Day4();
        day.setName("Camp Cleanup");
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    public static int part1(List<String> input) {
        int count = 0;
        for(String line : input) {
            String[] sections = line.split(",");
            Assignment section1 = getSectionFromString(sections[0]);
            Assignment section2 = getSectionFromString(sections[1]);
            if (section1.fullyContains(section2) || section2.fullyContains(section1)) {
                count++;
            }
        }
        return count;
    }

    public static int part2(List<String> input) {
        int count = 0;
        for(String line : input) {
            String[] sections = line.split(",");
            Assignment section1 = getSectionFromString(sections[0]);
            Assignment section2 = getSectionFromString(sections[1]);
            if (section1.overlap(section2)) {
                count++;
            }
        }
        return count;
    }

    private static Assignment getSectionFromString(String section) {
        String[] split = section.split("-");
        return new Assignment(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }

    private record Assignment (int min, int max) {
        public boolean fullyContains(Assignment other) {
            return this.min <= other.min && this.max >= other.max;
        }
        public boolean overlap(Assignment other) {
            return this.min <= other.max && this.max >= other.min;
        }
    }
}