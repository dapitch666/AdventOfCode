package org.anne.aoc2021;

import org.anne.common.Day;

import java.awt.*;
import java.util.OptionalInt;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Day17 extends Day {

    public static void main(String[] args) {
        new Day17().run();
    }

    @Override
    public void execute() {
        setName("Trick Shot");
        String input = readFileOneLine();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(String  input) {
        Rectangle target = getTarget(input);
        int radius = target.width * 10;
        return IntStream.range(-radius, radius)
                .boxed()
                .flatMap(x -> IntStream.range(-radius, radius)
                        .mapToObj(y -> new Point(x, y)))
                .mapToInt(p -> getHighestPoint(target, p).orElse(0))
                .max()
                .orElse(0);
    }



    public static long part2(String  input) {
        Rectangle target = getTarget(input);
        int radius = target.width * 10;
        return IntStream.range(-radius, radius)
                .boxed()
                .flatMap(x -> IntStream.range(-radius, radius)
                        .mapToObj(y -> new Point(x, y)))
                .mapToInt(p -> getHighestPoint(target, p).orElse(Integer.MIN_VALUE))
                .filter(i -> i != Integer.MIN_VALUE)
                .count();
    }

    public static OptionalInt getHighestPoint(Rectangle target, Point velocity) {
        Point probe = new Point(0, 0);
        int highestElevation = probe.y;
        while (probe.y > target.y) {
            probe.move(probe.x + velocity.x, probe.y + velocity.y);
            if (velocity.x > 0) {
                velocity.x -= 1;
            } else if (velocity.x < 0) {
                velocity.x += 1;
            }
            velocity.y--;
            if(probe.y > highestElevation) {
                highestElevation = probe.y;
            }
            if (target.contains(probe)) {
                return OptionalInt.of(highestElevation);
            }
        }
        return OptionalInt.empty();
    }

    static Rectangle getTarget(String input) {
        Pattern pattern = Pattern.compile("x=(-?\\d+)\\.\\.(-?\\d+), y=(-?\\d+)\\.\\.(-?\\d+)");
        Matcher matcher = pattern.matcher(input);
        int a = 0, b = 0, c = 0, d = 0;
        while (matcher.find()) {
            a = Integer.parseInt(matcher.group(1));
            b = Integer.parseInt(matcher.group(2));
            c = Integer.parseInt(matcher.group(3));
            d = Integer.parseInt(matcher.group(4));
        }
        return new Rectangle(a, c, (b - a) + 1, (d - c) + 1);
    }
}
