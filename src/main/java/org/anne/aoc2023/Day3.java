package org.anne.aoc2023;

import org.anne.common.Day;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Day3 extends Day {
    public static void main(String[] args) {
        Day day = new Day3();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Gear Ratios");
        List<String> input = this.readFile();
        this.setPart1(part1(input));
        this.setPart2(part2(input));
        this.printParts();
    }



    public static int part1(List<String> input) {
        var numString = new StringBuilder();
        List<Part> parts = new ArrayList<>();
        Set<Point> loc = new HashSet<>();
        Set<Point> symbols = new HashSet<>();
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                var c = input.get(i).charAt(j);
                if (c >= '0' && c <= '9') {
                    numString.append(c);
                    loc.add(new Point(j, i));
                } else {
                    if (c != '.') {
                        symbols.add(new Point(j, i));
                    }
                    if (!numString.isEmpty()) {
                        parts.add(new Part(loc, Integer.parseInt(numString.toString())));
                        numString = new StringBuilder();
                        loc = new HashSet<>();
                    }
                }
            }
            if (!numString.isEmpty()) {
                parts.add(new Part(loc, Integer.parseInt(numString.toString())));
                numString = new StringBuilder();
                loc = new HashSet<>();
            }
        }
        return parts.stream()
                .filter(x -> x.location().stream().anyMatch(y -> isNextToASymbol(y, symbols)))
                .mapToInt(Part::number)
                .sum();
    }

    public static long part2(List<String> input) {
        long gearRatios = 0;
        var numString = new StringBuilder();
        List<Part> parts = new ArrayList<>();
        Set<Point> loc = new HashSet<>();
        Set<Point> symbols = new HashSet<>();
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                var c = input.get(i).charAt(j);
                if (c >= '0' && c <= '9') {
                    numString.append(c);
                    loc.add(new Point(j, i));
                } else {
                    if (c == '*') {
                        symbols.add(new Point(j, i));
                    }
                    if (!numString.isEmpty()) {
                        parts.add(new Part(loc, Integer.parseInt(numString.toString())));
                        numString = new StringBuilder();
                        loc = new HashSet<>();
                    }
                }
            }
            if (!numString.isEmpty()) {
                parts.add(new Part(loc, Integer.parseInt(numString.toString())));
                numString = new StringBuilder();
                loc = new HashSet<>();
            }
        }
        for (var symbol : symbols) {
            Set<Part> adjacent = parts.stream()
                    .filter(x -> isNextTo(symbol, x))
                    .collect(Collectors.toSet());
            if (adjacent.size() == 2) {
                gearRatios += adjacent.stream().mapToLong(Part::number).reduce(1, (a, b) -> a * b);
            }
        }
        return gearRatios;
    }
    
    record Part(Set<Point> location, int number) {
    }
    
    static boolean isNextToASymbol(Point p, Set<Point> symbols) {
        return symbols.stream().anyMatch(x -> isAdjacent(p, x));
    }
    
    static boolean isNextTo(Point p, Part part) {
        return part.location().stream().anyMatch(x -> isAdjacent(p, x));
    }
    
    static boolean isAdjacent(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) <= 1 && Math.abs(p1.y - p2.y) <= 1;
    }
}
