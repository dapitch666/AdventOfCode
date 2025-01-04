package org.anne.aoc2018;

import org.anne.common.Day;

import java.util.*;

public class Day9 extends Day {
    public static void main(String[] args) {
        new Day9().run();
    }

    @Override
    public void execute() {
        setName("Marble Mania");
        String input = readFileOneLine();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static long part1(String input) {
        int[] data = Arrays.stream(input.split(" ")).filter(s -> s.matches("\\d+")).mapToInt(Integer::parseInt).toArray();
        return getWinningScore(data[0], data[1]);
    }

    private static long getWinningScore(int playerCount, int lastMarble) {
        MarbleCircle circle = new MarbleCircle();
        long[] scores = new long[playerCount];
        circle.addClockwise(new Marble(0));

        for (int i = 1; i <= lastMarble; i++) {
            if (i % 23 != 0) {
                circle.addClockwise(new Marble(i));
            } else {
                scores[(i - 1) % scores.length] += i + circle.removeCounterClockwise();
            }
        }
        return Arrays.stream(scores).max().orElse(0);
    }

    public static long part2(String input) {
        int[] data = Arrays.stream(input.split(" ")).filter(s -> s.matches("\\d+")).mapToInt(Integer::parseInt).toArray();
        return getWinningScore(data[0], data[1] * 100);
    }

    static class MarbleCircle {
        private Marble current = null;

        void addClockwise(Marble marble) {
            if (current == null) {
                current = marble;
                current.setNext(current);
                current.setPrevious(current);
            } else {
                Marble left = current.getNext();
                Marble right = left.getNext();
                left.setNext(marble);
                marble.setPrevious(left);
                marble.setNext(right);
                right.setPrevious(marble);
                current = marble;
            }
        }

        int removeCounterClockwise() {
            if (current == null) {
                return 0;
            }

            Marble removed = current;
            for (int i = 0; i < 7; i++) {
                removed = removed.getPrevious();
            }
            Marble left = removed.getPrevious();
            Marble right = removed.getNext();
            left.setNext(right);
            right.setPrevious(left);
            current = right;
            return removed.getValue();
        }
    }

    static class Marble {
        private final int value;
        private Marble previous;
        private Marble next;

        public Marble(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public Marble getPrevious() {
            return previous;
        }

        public void setPrevious(Marble previous) {
            this.previous = previous;
        }

        public Marble getNext() {
            return next;
        }

        public void setNext(Marble next) {
            this.next = next;
        }
    }
}
