package org.anne.aoc2017;

import org.anne.common.Day;
import org.anne.common.Direction;
import org.anne.common.GridHelper;

import java.awt.*;
import java.util.List;

public class Day19 extends Day {
    public static void main(String[] args) {
        new Day19().run();
    }

    @Override
    public void execute() {
        setName("A Series of Tubes");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static String part1(List<String> input) {
        return getResult(input, true);
    }

    public static int part2(List<String> input) {
        return Integer.parseInt(getResult(input, false));
    }

    private static String getResult(List<String> input, boolean part1) {
        var grid = GridHelper.getCharGrid(input);
        Point point = new Point(input.getFirst().indexOf('|'), 0);
        Direction direction = Direction.SOUTH;
        StringBuilder result = new StringBuilder();
        int steps = 0;
        while (true) {
            point = direction.move(point);
            char c = GridHelper.get(point, grid);
            steps++;
            switch (c) {
                case '|', '-':
                    break;
                case '+':
                    for (Direction d : Direction.values()) {
                        if (d != direction && d != direction.reverse() && GridHelper.isValidPoint(d.move(point), grid, ' ')) {
                            direction = d;
                            break;
                        }
                    }
                    break;
                case ' ':
                    return part1 ? result.toString() : String.valueOf(steps);
                default:
                    result.append(c);
            }
        }
    }
}
