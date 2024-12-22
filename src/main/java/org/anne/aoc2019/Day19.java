package org.anne.aoc2019;

import org.anne.common.Day;

import java.awt.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day19 extends Day {
    public static void main(String[] args) {
        new Day19().run();
    }

    @Override
    public void execute() {
        setName("Tractor Beam");
        String input = readFileOneLine();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    static int part1(String input) {
        return getBeam(input).size();
    }

    static int part2(String input) {
        Set<Point> beam = getBeam(input);
        Point current = beam.stream()
                .filter(p -> p.x == 49)
                .map(p -> p.y)
                .max(Integer::compareTo)
                .map(y -> new Point(49, y))
                .orElseThrow();
        while (true) {
            if (isInBeam(current, input)) {
                if (isInBeam(new Point(current.x + 99, current.y - 99), input)) {
                    return current.x * 10000 + current.y - 99;
                }
                current.translate(0, 1);
            } else {
                current.translate(1, 0);
            }
        }
    }

    static Set<Point> getBeam(String input) {
        return IntStream.range(0, 50)
                .boxed()
                .flatMap(y -> IntStream.range(0, 50).mapToObj(x -> new Point(x, y)))
                .filter(p -> isInBeam(p, input))
                .collect(Collectors.toSet());
    }

    static boolean isInBeam(Point p, String input) {
        Computer computer = new Computer(input);
        return computer.computeAndGetOutput(p.x, p.y) == 1;
    }
}
