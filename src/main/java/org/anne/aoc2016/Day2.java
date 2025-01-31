package org.anne.aoc2016;

import org.anne.common.Day;
import org.anne.common.Direction;
import org.anne.common.GridHelper;

import java.awt.*;
import java.util.List;

public class Day2 extends Day {
    public static void main(String[] args) {
        new Day2().run();
    }

    @Override
    public void execute() {
        setName("Bathroom Security");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static String part1(List<String> input) {
        return getCode(input, new char[][]{
            {'1', '2', '3'},
            {'4', '5', '6'},
            {'7', '8', '9'}
        });
    }

    public static String part2(List<String> input) {
        return getCode(input, new char[][]{
            {'.', '.', '1', '.', '.'},
            {'.', '2', '3', '4', '.'},
            {'5', '6', '7', '8', '9'},
            {'.', 'A', 'B', 'C', '.'},
            {'.', '.', 'D', '.', '.'}
        });
    }

    private static String getCode(List<String> input, char[][] grid) {
        Point position = GridHelper.findChar(grid, '5');
        StringBuilder code = new StringBuilder();
        for (String line : input) {
            for (char c : line.toCharArray()) {
                Point next = Direction.fromArrowChar(c).move(position);
                if (GridHelper.isValidPoint(next, grid, '.')) {
                    position = next;
                }
            }
            code.append(GridHelper.get(position, grid));
        }
        return code.toString();
    }
}