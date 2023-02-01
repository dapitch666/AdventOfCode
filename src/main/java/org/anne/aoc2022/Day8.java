package org.anne.aoc2022;

import org.anne.common.Day;
import org.anne.common.Utils;

import java.util.List;

public class Day8 extends Day {
    public static void main(String[] args) {
        Day day = new Day8();
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    public static int part1(List<String> input) {
        int size = input.size();
        int visibleTrees = size * 4 - 4;
        List<String> transposedInput = Utils.transpose(input);
        for (int i = 1; i < size - 1; i++) {
            for (int j = 1; j < size - 1; j++) {
                int c = input.get(i).charAt(j);
                if (c > input.get(i).substring(0, j).chars().max().orElse(-1)
                        || c > input.get(i).substring(j+1).chars().max().orElse(-1)
                        || c > transposedInput.get(j).substring(0, i).chars().max().orElse(-1)
                        || c > transposedInput.get(j).substring(i+1).chars().max().orElse(-1)) {
                    visibleTrees++;
                }
            }
        }
        return visibleTrees;
    }

    public static int part2(List<String> input) {
        int size = input.size();
        int higherScore = 0;
        List<String> transposedInput = Utils.transpose(input);
        for (int i = 1; i < size - 1; i++) {
            for (int j = 1; j < size - 1; j++) {
                char c = input.get(i).charAt(j);
                higherScore = Math.max(higherScore,
                        calcScore(c, new StringBuilder(input.get(i).substring(0, j)).reverse().toString()) *
                                calcScore(c, input.get(i).substring(j+1)) *
                                calcScore(c, new StringBuilder(transposedInput.get(j).substring(0, i)).reverse().toString()) *
                                calcScore(c, transposedInput.get(j).substring(i+1)));

            }
        }
        return higherScore;
    }

    static int calcScore(char c, String neighbours) {
        int score = 0;
        for(char neighbour : neighbours.toCharArray()) {
            if (c > neighbour) {
                score++;
            } else {
                score++;
                break;
            }
        }
        return score;
    }
}
