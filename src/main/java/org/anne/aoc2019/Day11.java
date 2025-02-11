package org.anne.aoc2019;

import org.anne.common.Day;
import org.anne.common.Utils;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Day11 extends Day {
    public static void main(String[] args) {
        new Day11().run();
    }

    @Override
    public void execute() {
        setName("Space Police");
        String input = readFileOneLine();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    static int part1(String input) {
        Set<Point> painted = new HashSet<>();
        Set<Point> white = new HashSet<>();
        Point current = new Point(0, 0);
        Direction direction = Direction.UP;
        Computer computer = new Computer(input);
        while (computer.isStillRunning()) {
            int color = white.contains(current) ? 1 : 0;
            computer.compute(color);
            long outColor = computer.getOutput();
            if (outColor != color) {
                painted.add(new Point(current));
                if (outColor == 1)
                    white.add(new Point(current));
                else if (outColor == 0)
                    white.remove(new Point(current));
            }
            long outDirection = computer.getOutput();
            direction = turn(direction, outDirection);
            current.translate(direction.x, direction.y);
        }
        return painted.size();
    }

    static String part2(String input) {
        Set<Point> white = new HashSet<>();
        Point current = new Point(0, 0);
        Direction direction = Direction.UP;
        Computer computer = new Computer(input);
        white.add(new Point(current));
        while (computer.isStillRunning()) {
            int color = white.contains(current) ? 1 : 0;
            computer.compute(color);
            long outColor = computer.getOutput();
            if (outColor != color) {
                if (outColor == 1)
                    white.add(new Point(current));
                else if (outColor == 0)
                    white.remove(new Point(current));
            }
            long outDirection = computer.getOutput();
            direction = turn(direction, outDirection);
            current.translate(direction.x, direction.y);
        }
        // System.out.println(Utils.print2dIntArray(Utils.getArray(white)));
        return Utils.ocr(Utils.getArray(white), 5, 6);
    }

    static Direction turn(Direction direction, long output) {
        if (output == 0L) {
            direction = Direction.values()[(direction.ordinal() + 3) % 4];
        } else {
            direction = Direction.values()[(direction.ordinal() + 1) % 4];
        }
        return direction;
    }



    enum Direction {
        UP(0, -1), RIGHT(1, 0), DOWN(0, 1), LEFT(-1, 0);

        public final int x;
        public final int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
