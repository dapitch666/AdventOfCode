package org.anne.aoc2021;

import org.anne.common.Day;
import org.anne.common.GridHelper;

import java.util.List;

public class Day25 extends Day {

    public static void main(String[] args) {
        new Day25().run();
    }

    @Override
    public void execute() {
        setName("Sea Cucumber");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2());
        printParts();
    }

    public static int part1(List<String>  input) {
        char[][] cucumbers = GridHelper.getCharGrid(input);
        int step = 1;
        while(move(cucumbers)) {
            step++;
        }
        return step;
    }

    @SuppressWarnings("SameReturnValue")
    public static String part2() {
        return "Merry Christmas!";
    }

    static boolean move(char[][] cucumbers) {
        boolean hasMoved = false;
        int height = cucumbers.length;
        int width = cucumbers[0].length;
        for (int i = 0; i < height; i++) {
            boolean moveEdge = cucumbers[i][0] == '.' && cucumbers[i][width - 1] == '>';
            for (int j = 0; j < width - 1; j++) {
                if (cucumbers[i][j] == '>') {
                    if (cucumbers[i][j + 1] == '.') {
                        hasMoved = true;
                        cucumbers[i][j] = '.';
                        cucumbers[i][j + 1] = '>';
                        j++;
                    }
                }
            }
            if (moveEdge) {
                hasMoved = true;
                cucumbers[i][width - 1] = '.';
                cucumbers[i][0] = '>';
            }
        }

        for (int i = 0; i < width; i++) {
            boolean moveEdge = cucumbers[0][i] == '.' && cucumbers[height - 1][i] == 'v';
            for (int j = 0; j < height - 1; j++) {
                if (cucumbers[j][i] == 'v') {
                    if (cucumbers[j + 1][i] == '.') {
                        hasMoved = true;
                        cucumbers[j][i] = '.';
                        cucumbers[j + 1][i] = 'v';
                        j++;
                    }
                }
            }
            if (moveEdge) {
                hasMoved = true;
                cucumbers[height - 1][i] = '.';
                cucumbers[0][i] = 'v';
            }
        }
        return hasMoved;
    }
}
