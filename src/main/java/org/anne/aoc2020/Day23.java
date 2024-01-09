package org.anne.aoc2020;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day23 extends Day {

    public static void main(String[] args) {
        Day day = new Day23();
        day.setName("Crab Cups");
        String input = day.readFileOneLine();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    static String part1(String input) {
        List<Integer> circle = Arrays.stream(input.split("")).map(Integer::parseInt).collect(Collectors.toList());
        int curCup = circle.get(0);
        for (int i = 0; i < 100; i++) {
            int curIndex = circle.indexOf(curCup);
            List<Integer> threeCups = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                threeCups.add(circle.get(curIndex + 1));
                circle.remove(curIndex + 1);
            }

            int destCup = curCup - 1;
            while (!circle.contains(destCup)) {
                if (destCup < 1) {
                    destCup = 9;
                } else {
                    destCup--;
                }
            }
            int destIndex = circle.indexOf(destCup);
            circle.add(destIndex + 1, threeCups.get(0));
            circle.add(destIndex + 2, threeCups.get(1));
            circle.add(destIndex + 3, threeCups.get(2));

            curCup = circle.get(circle.indexOf(curCup) + 1);
            List<Integer> tmpCircle = new ArrayList<>();
            for (int j = circle.indexOf(curCup); j < circle.size(); j++) {
                tmpCircle.add(circle.get(j));
            }
            for (int j = 0; j < circle.indexOf(curCup); j++) {
                tmpCircle.add(circle.get(j));
            }
            circle = tmpCircle;
        }
        List<String> finalCircle = new ArrayList<>();
        for (int j = circle.indexOf(1) + 1; j < circle.size(); j++) {
            finalCircle.add(String.valueOf(circle.get(j)));
        }
        for (int j = 0; j < circle.indexOf(1); j++) {
            finalCircle.add(String.valueOf(circle.get(j)));
        }
        return finalCircle.stream().map(String::valueOf).collect(Collectors.joining());
    }

    static long part2(String input) {
        int[] next = new int[1000001];
        int[] circle = Arrays.stream(input.split("")).mapToInt(Integer::parseInt).toArray();
        for(int i = 1; i < circle.length; i++) {
            next[circle[i-1]] = circle[i];
        }
        next[circle[circle.length - 1]] = 10;
        for(int i = 11; i < 1000001; i++) {
            next[i-1] = i;
        }
        next[1000000] = circle[0];
        int cur = circle[0];
        for(int i = 0; i < 10000000; i++) {
            int first = next[cur];
            int second = next[first];
            int third = next[second];
            int dest = cur - 1;
            if(dest == 0)
                dest = 1000000;
            while(dest == first || dest == second || dest == third) {
                dest--;
                if(dest == 0)
                    dest = 1000000;
            }
            next[cur] = next[third];
            next[third] = next[dest];
            next[dest] = first;
            cur = next[cur];
        }
        long label1 = next[1];
        long label2 = next[next[1]];

        return label1 * label2;
    }
}
