package org.anne.aoc2020;

import org.anne.common.Day;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Day5 extends Day {

    public static void main(String[] args) {
        new Day5().run();
    }

    @Override
    public void execute() {
        setName("Binary Boarding");
        SortedSet<Integer> input = getBoardingPasses(readFile());
        setPart1(part1(input));
        setPart2(part2(input));
    }

    static int part1(SortedSet<Integer> boardingPasses) {
        return boardingPasses.last();
    }

    static int part2(SortedSet<Integer> boardingPasses) {
        int i = boardingPasses.first();
        for (int seat : boardingPasses) {
            if (seat != i) {
                break;
            }
            i++;
        }
        return i;
    }

    private static SortedSet<Integer> getBoardingPasses(List<String> input) {
        SortedSet<Integer> boardingPasses = new TreeSet<>();
        for (String ticket : input) {
            int row = decodeTicket(ticket, true);
            int column = decodeTicket(ticket, false);
            boardingPasses.add(row * 8 + column);
        }
        return boardingPasses;
    }

    static int decodeTicket(String ticket, boolean isRow) {
        int min = 0;
        int max;
        char up;
        char down;
        if (isRow) {
            max = 127;
            up = 'F';
            down = 'B';
        } else {
            max = 7;
            up = 'L';
            down = 'R';
        }
        for (char c : ticket.toCharArray()) {
            int boundary =  ((max - min + 1) / 2) + min;
            if (c == up) {
                max = boundary - 1;
            } else if (c == down){
                min = boundary;
            }
        }
        return min;
    }
}
