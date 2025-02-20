package org.anne.aoc2020;

import org.anne.common.Day;

import java.util.*;

public class Day15 extends Day {

    public static void main(String[] args) {
        new Day15().run();
    }

    @Override
    public void execute() {
        setName("Rambunctious Recitation");
        List<Integer> input = readFileIntegerOneLine(",");
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(List<Integer> input) {
        return getLastSpokenNumber(input, 2020);
    }

    public static int part2(List<Integer> input) {
        return getLastSpokenNumber(input, 30000000);
    }

   static int getLastSpokenNumber(List<Integer> input, int maxTurns) {
       int[] birthTime = new int[maxTurns];
       boolean[] hasKey = new boolean[maxTurns];
       int spokenNumber = 0, nextNumber;

       for (int i = 0; i < maxTurns; i++) {
           if (i < input.size()) {
               nextNumber = input.get(i);
           } else {
               if (!hasKey[spokenNumber]) {
                   nextNumber = 0;
               } else {
                   nextNumber = i - birthTime[spokenNumber];
               }
           }
           if (i != 0) {
               birthTime[spokenNumber] = i;
               hasKey[spokenNumber] = true;
           }
           spokenNumber = nextNumber;
       }
       return spokenNumber;
    }
}
