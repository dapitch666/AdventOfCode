package org.anne.aoc2021;

import org.anne.common.Day;
import org.anne.common.Utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        return fold(sheet, folds.getFirst()).size();
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
        // System.out.println(Utils.printAscii(sheet));
        return Utils.ocr(Utils.getArray(sheet), 5, 6);
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
