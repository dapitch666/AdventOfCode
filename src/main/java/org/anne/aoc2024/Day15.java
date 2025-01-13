package org.anne.aoc2024;

import org.anne.common.Day;
import org.anne.common.Direction;
import org.anne.common.GridHelper;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Day15 extends Day {
    public static void main(String[] args) {
        new Day15().run();
    }

    @Override
    public void execute() {
        setName("Warehouse Woes");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(List<String> input) {
        List<String> inputGrid = new ArrayList<>(input.subList(0, input.indexOf("")));
        char[][] grid = GridHelper.getCharGrid(inputGrid);
        Point robot = GridHelper.findChar(grid, '@');
        grid[robot.y][robot.x] = '.';
        for (var direction : movements(input, input.indexOf(""))) {
            Point next = direction.move(robot);
            char nextChar = grid[next.y][next.x];
            if (nextChar == '.') {
                robot = next;
            } else if (nextChar == 'O') {
                Point nextEmpty = getNextEmpty(direction, next, grid);
                if (nextEmpty.x != -1) {
                    robot = next;
                    grid[robot.y][robot.x] = '.';
                    grid[nextEmpty.y][nextEmpty.x] = 'O';
                }
            }
        }
        return getGPSSum(grid);
    }

    public static int part2(List<String> input) {
        int emptyLineIndex = input.indexOf("");
        char[][] grid = new char[emptyLineIndex][emptyLineIndex * 2];
        Point robot = new Point(-1, -1);

        for (int y = 0; y < emptyLineIndex; y++) {
            for (int x = 0; x < emptyLineIndex; x++) {
                char c = input.get(y).charAt(x);
                switch (c) {
                    case '#', '.' -> {
                        grid[y][x * 2] = c;
                        grid[y][(x * 2) + 1] = c;
                    }
                    case 'O' -> {
                        grid[y][x * 2] = '[';
                        grid[y][(x * 2) + 1] = ']';
                    }
                    case '@' -> {
                        robot = new Point(x * 2, y);
                        grid[y][x * 2] = '.';
                        grid[y][(x * 2) + 1] = '.';
                    }
                }
            }
        }

        for (var direction : movements(input, emptyLineIndex)) {
            Point next = direction.move(robot);
            char nextChar = grid[next.y][next.x];
            if (nextChar == '.') {
                robot = next;
            } else if (nextChar == '[' || nextChar == ']') {
                if (direction == Direction.EAST || direction == Direction.WEST) {
                    Point nextEmpty = getNextEmpty(direction, next, grid);
                    if (nextEmpty.x != -1) {
                        Point nextPoint = direction.move(next);
                        for (int i = 0; i < Math.abs(nextEmpty.x - next.x); i++) {
                            char c1 = grid[nextPoint.y][nextPoint.x];
                            grid[nextPoint.y][nextPoint.x] = (c1 == '[') ? ']' : (c1 == ']') ? '[' : (direction == Direction.EAST ? ']' : '[');
                            nextPoint = direction.move(nextPoint);
                        }
                        robot = next;
                        grid[robot.y][robot.x] = '.';
                    }
                } else {
                    if (nextChar == '[' && canMove(grid, next, direction)) {
                        move(grid, next, direction);
                        robot = next;
                        grid[robot.y][robot.x] = '.';
                    } else if (nextChar == ']' && canMove(grid, new Point(next.x - 1, next.y), direction)) {
                        move(grid, new Point(next.x - 1, next.y), direction);
                        robot = next;
                        grid[robot.y][robot.x] = '.';
                    }
                }
            }
        }
        return getGPSSum(grid);
    }

    private static int getGPSSum(char[][] grid) {
        int sum = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (grid[y][x] == 'O' || grid[y][x] == '[') {
                    sum += x + 100 * y;
                }
            }
        }
        return sum;
    }

    static List<Direction> movements(List<String> input, int emptyLineIndex) {
        List<Direction> movements = new ArrayList<>();
        for (char c : String.join("", input.subList(emptyLineIndex + 1, input.size())).toCharArray()) {
            movements.add(Direction.fromChar(c));
        }
        return movements;
    }

    private static Point getNextEmpty(Direction direction, Point next, char[][] grid) {
        Point current = next;
        while (true) {
            current = direction.move(current);
            char c = grid[current.y][current.x];
            if (c == '.') {
                return current;
            } else if (c == '#') {
                return new Point(-1, -1);
            }
        }
    }

    private static boolean canMove(char[][] grid, Point point, Direction direction) {
        Point next = direction.move(point);

        boolean left = switch (grid[next.y][next.x]) {
            case '.' -> true;
            case '[' -> canMove(grid, next, direction);
            case ']' -> canMove(grid, new Point(next.x - 1, next.y), direction);
            default -> false; // #
        };
        if (!left) {
            return false;
        }
        next = new Point(next.x + 1, next.y);
        return switch (grid[next.y][next.x]) {
            case '.', ']' -> true; // ] already checked in left
            case '[' -> canMove(grid, next, direction);
            default -> false; // #
        };
    }

    private static void move(char[][] grid, Point point, Direction direction) {
        Point next = direction.move(point);
        char[] newRow = grid[next.y];
        char c = newRow[point.x];
        if (c == '[') {
            move(grid, next, direction);
        } else if (c == ']') {
            move(grid, new Point(next.x - 1, next.y), direction);
        }
        c = newRow[point.x + 1];
        if (c == '[') {
            move(grid, new Point(point.x + 1, next.y), direction);
        }
        newRow[point.x] = '[';
        newRow[point.x + 1] = ']';
        grid[point.y][point.x] = '.';
        grid[point.y][point.x + 1] = '.';
    }
}
