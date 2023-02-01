package org.anne.aoc2022;

import org.anne.common.Day;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day2 extends Day {
    public static void main(String[] args) {
        Day day = new Day2();
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    public static int part1(List<String> input) {
        int score = 0;
        for (String s : input) {
            String[] round = s.split(" ");
            Hand him = Hand.valueOfLetter(round[0]);
            Hand me = Hand.valueOfLetter(round[1]);
            if (me.equals(him)) {
                score += Result.DRAW.points + me.points;
            } else {
                score += me.win(him) ? Result.WIN.points + me.points : me.points;
            }
        }
        return score;
    }

    public static int part2(List<String> input) {
        int score = 0;
        for (String s : input) {
            String[] round = s.split(" ");
            Hand him = Hand.valueOfLetter(round[0]);
            Result res = Result.valueOfLetter(round[1]);
            if (res.equals(Result.DRAW)) {
                score += Result.DRAW.points + him.points;
            } else if (res.equals(Result.LOOSE)) {
                score += him.looserOf().points;
            } else {
                score += Result.WIN.points + him.winnerOf().points;
            }
        }
        return score;
    }

    public enum Result {
        LOOSE(0, "X"),
        DRAW(3, "Y"),
        WIN(6, "Z");

        public final int points;
        public final String letter;

        Result(int points, String letter) {
            this.points = points;
            this.letter = letter;
        }

        public static Result valueOfLetter(String letter) {
            return switch (letter) {
                case "X" -> Result.LOOSE;
                case "Y" -> Result.DRAW;
                case "Z" -> Result.WIN;
                default -> null;
            };
        }
    }

    public enum Hand {
        ROCK(1),
        PAPER(2),
        SCISSOR(3);

        public final int points;
        private static final Map<Hand, Hand> rules = new HashMap<>();

        static {
            rules.put(Hand.ROCK, Hand.SCISSOR);
            rules.put(Hand.PAPER, Hand.ROCK);
            rules.put(Hand.SCISSOR, Hand.PAPER);
        }

        Hand(int points) {
            this.points = points;
        }

        public static Hand valueOfLetter(String letter) {
            return switch (letter) {
                case "A", "X" -> Hand.ROCK;
                case "B", "Y" -> Hand.PAPER;
                case "C", "Z" -> Hand.SCISSOR;
                default -> null;
            };
        }

        public boolean win(Hand other) {
            return rules.get(this).equals(other);
        }

        public Hand looserOf() {
            return rules.get(this);
        }

        public Hand winnerOf() {
            return rules
                    .entrySet()
                    .stream()
                    .filter(e -> this.equals(e.getValue()))
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElseThrow();
        }
    }

}
