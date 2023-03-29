package org.anne.aoc2019;

import org.anne.common.Day;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.anne.common.Constants.LINE_SEPARATOR;

public class Day17 extends Day {

    public static void main(String[] args) {
        Day day = new Day17();
        String input = day.readFileOneLine();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    static int part1(String input) {
        List<String> cameraInput = getCameraInput(input);
        return getSum(cameraInput);
    }

    static int getSum(List<String> cameraInput) {
        Set<Point> map = getMap(cameraInput);
        int sum = 0;
        for (Point point : map) {
            if (map.contains(new Point(point.x - 1, point.y))
                    && map.contains(new Point(point.x + 1, point.y))
                    && map.contains(new Point(point.x, point.y - 1))
                    && map.contains(new Point(point.x, point.y + 1))) {
                sum += point.x * point.y;
            }
        }
        return sum;
    }


    static long part2(String input) {
        List<String> cameraInput = getCameraInput(input);
        List<String> instructions = getInstructions(cameraInput);
        String newInput = getNewInput(20, instructions);
        input = input.replaceFirst("1", "2");
        Computer computer = new Computer(input);
        for (var c : newInput.toCharArray()) {
            computer.writeInput(c);
        }
        computer.compute();
        return computer.getLastOutput();
    }

    static String getNewInput(int memory, List<String> instructions) {
        String functionA = "";
        String functionB = "";
        String functionC = "";
        String input = String.join(",", instructions) + ",";
        String regex = String.format("^(.{1,%s})\\1*(.{1,%s})(?:\\1|\\2)*(.{1,%s})(?:\\1|\\2|\\3)*$", memory, memory, memory);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            functionA = matcher.group(1);
            functionB = matcher.group(2);
            functionC = matcher.group(3);
        }
        input = input.replaceAll(functionA, "A,")
                .replaceAll(functionB, "B,")
                .replaceAll(functionC, "C,")
                .replaceAll(",$", LINE_SEPARATOR)
                + functionA.replaceAll(",$", LINE_SEPARATOR)
                + functionB.replaceAll(",$", LINE_SEPARATOR)
                + functionC.replaceAll(",$", LINE_SEPARATOR)
                + "n" + LINE_SEPARATOR;
        return input;
    }

    static Point getStart(List<String> input) {
        return input.stream()
                .filter(s -> s.contains("^"))
                .map(s -> new Point(s.indexOf("^"), input.indexOf(s)))
                .findFirst()
                .orElseThrow();
    }

    static List<String> getInstructions(List<String> input) {
        Set<Point> map = getMap(input);
        Point start = getStart(input);
        List<String> instructions = new ArrayList<>();
        Point current = start;
        Direction direction = Direction.UP;
        while (true) {
            Point next = moveForward(current, direction);
            if (map.contains(next)) {
                int steps = 0;
                while (map.contains(next)) {
                    steps++;
                    current = next;
                    next = moveForward(current, direction);
                }
                instructions.add(String.valueOf(steps));
            } else {
                Direction newDirection = direction.turnLeft();
                next = moveForward(current, newDirection);
                if (map.contains(next)) {
                    instructions.add("L");
                    direction = newDirection;
                    continue;
                }
                newDirection = direction.turnRight();
                next = moveForward(current, newDirection);
                if (map.contains(next)) {
                    instructions.add("R");
                    direction = newDirection;
                    continue;
                }
                return instructions;
            }
        }
    }

    private static Point moveForward(Point current, Direction direction) {
        return new Point(current.x + direction.x, current.y + direction.y);
    }

    static Set<Point> getMap(List<String> input) {
        Set<Point> map = new HashSet<>();
        int y = -1;
        for (String line : input) {
            y++;
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == '#') {
                    map.add(new Point(x, y));
                }
            }
        }
        return map;
    }

    private static List<String> getCameraInput(String input) {
        Computer computer = new Computer(input);
        computer.compute();
        var cameraOutput = computer.getOutputs().stream()
                .map(i -> (char) i.intValue())
                .map(Object::toString)
                .collect(Collectors.joining());
        return Arrays.stream(cameraOutput.split(LINE_SEPARATOR))
                .collect(Collectors.toList());
    }

    enum Direction {
        UP(0, -1), RIGHT(1, 0), DOWN(0, 1), LEFT(-1, 0);

        public final int x;
        public final int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Direction turnRight() {
            return switch (this) {
                case UP -> RIGHT;
                case RIGHT -> DOWN;
                case DOWN -> LEFT;
                case LEFT -> UP;
            };
        }

        Direction turnLeft() {
            return switch (this) {
                case UP -> LEFT;
                case RIGHT -> UP;
                case DOWN -> RIGHT;
                case LEFT -> DOWN;
            };
        }
    }
}
