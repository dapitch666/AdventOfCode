package org.anne.aoc2021;

import org.anne.common.Day;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.anne.common.Constants.LINE_SEPARATOR;

public class Day13 extends Day {

    public static void main(String[] args) {
        new Day13().run();
    }

    @Override
    public void execute() {
        setName("Transparent Origami");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    static long part1(List<String> input) {
        Set<Point> sheet = new HashSet<>();
        List<String> folds = new ArrayList<>();

        Pattern pattern = Pattern.compile("(\\d+),(\\d+)");
        for (String line : input) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches()) {
                sheet.add(new Point(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))));
            } else if (!line.isEmpty()) {
                String[] split = line.split(" ");
                folds.add(split[2]);
            }
        }
        return fold(sheet, folds.get(0)).size();
    }

    static String part2(List<String> input) {
        Set<Point> sheet = new HashSet<>();
        List<String> folds = new ArrayList<>();

        Pattern pattern = Pattern.compile("(\\d+),(\\d+)");
        for (String line : input) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches()) {
                sheet.add(new Point(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))));
            } else if (!line.isEmpty()) {
                String[] split = line.split(" ");
                folds.add(split[2]);
            }
        }
        for (String fold : folds) {
            sheet = fold(sheet, fold);
        }
        return printCode(sheet);
    }

    private static String printCode(Set<Point> sheet) {
        int maxX = sheet.stream().mapToInt(p -> p.x).max().orElse(0) + 1;
        int maxY = sheet.stream().mapToInt(p -> p.y).max().orElse(0) + 1;
        StringBuilder code = new StringBuilder();
        for (int y = 0; y < maxY; y++ ) {
            for (int x = 0; x < maxX; x++ ) {
                if (sheet.contains(new Point(x, y))) {
                    code.append('#');
                } else {
                    code.append(' ');
                }
            }
            code.append(LINE_SEPARATOR);
        }
        return code.toString();
    }

    static Set<Point> fold (Set<Point> sheet, String instruction) {
        String[] split = instruction.split("=");
        int foldPosition = Integer.parseInt(split[1]);
        boolean foldIsHorizontal = split[0].equals("y");
        Set<Point> foldedSheet = new HashSet<>();
        for (Point dot : sheet){
            if (foldIsHorizontal) {
                foldedSheet.add(new Point(
                        dot.x,
                        dot.y > foldPosition ? 2 * foldPosition - dot.y : dot.y));
            } else {
                foldedSheet.add(new Point(
                        dot.x > foldPosition ? 2 * foldPosition - dot.x : dot.x,
                        dot.y));
            }
        }
        return foldedSheet;
    }
}
