package org.anne.aoc2021;

import org.anne.common.Day;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Day21 extends Day {

    public static void main(String[] args) {
        new Day21().run();
    }

    @Override
    public void execute() {
        setName("Dirac Dice");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static long part1(List<String>  input) {
        int player1Position = Character.getNumericValue(input.get(0).charAt(input.get(0).length() - 1));
        int player2Position = Character.getNumericValue(input.get(1).charAt(input.get(1).length() - 1));
        long player1Score = 0;
        long player2Score = 0;
        int dice = 0;
        int counter = 0;
        boolean isPlayer1Turn = true;
        while (Math.max(player1Score, player2Score) < 1000) {
            int roll = 0;
            for (int i = 0; i < 3; i++) {
                if (dice < 100) dice ++;
                else dice = dice + 1 - 100;
                roll += dice;
            }
            if (isPlayer1Turn) {
                player1Position += roll % 10;
                if (player1Position > 10) {
                    player1Position -= 10;
                }
                player1Score += player1Position;
            } else {
                player2Position += roll % 10;
                if (player2Position > 10) {
                    player2Position -= 10;
                }
                player2Score += player2Position;
            }
            counter += 3;
            isPlayer1Turn = !isPlayer1Turn;
            // System.out.println("Player " + (isPlayer1Turn ? "2" : "1") + " rolls " + roll + " and moves to space " + (isPlayer1Turn ? player2Position : player1Position) + " for a total score of " + (isPlayer1Turn ? player2Score : player1Score) + ".");
        }
        return counter * Math.min(player1Score, player2Score);
    }

    public static long part2(List<String>  input) {
        Universe startingUniverse = new Universe(
                Character.getNumericValue(input.get(0).charAt(input.get(0).length() - 1)),
                Character.getNumericValue(input.get(1).charAt(input.get(1).length() - 1)),
                0, 0, true);
        long[] scores = splitUniverse(startingUniverse, new HashMap<>());

        return Math.max(scores[0], scores[1]);
    }

    private static long[] splitUniverse(Universe universe, Map<Universe, long[]> map) {
        long[] wins = {0L, 0L};
        if (map.containsKey(universe)) {
            return map.get(universe);
        } else if (universe.getMaxScore() >= 21) {
            return universe.score();
        }
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                for (int k = 1; k <= 3; k++) {
                    Universe newUniverse = new Universe(universe.player1Position, universe.player2Position,
                            universe.player1Score, universe.player2Score, universe.isPlayer1Turn, i + j + k);
                    long[] newWins = splitUniverse(newUniverse, map);
                    wins[0] += newWins[0];
                    wins[1] += newWins[1];
                }
            }
        }
        map.put(universe, wins);
        return wins;
    }

    static class Universe {
        final int player1Position;
        final int player2Position;
        final long player1Score;
        final long player2Score;
        final boolean isPlayer1Turn;

        public Universe(int player1Position, int player2Position, long player1Score, long player2Score, boolean isPlayer1Turn) {
            this.player1Position = player1Position;
            this.player2Position = player2Position;
            this.player1Score = player1Score;
            this.player2Score = player2Score;
            this.isPlayer1Turn = isPlayer1Turn;
        }

        public Universe(int player1InitialPosition, int player2InitialPosition, long player1InitialScore,
                        long player2InitialScore, boolean isPlayer1Turn, int roll) {
            if (isPlayer1Turn) {
                this.player2Position = player2InitialPosition;
                this.player2Score = player2InitialScore;
                int position = player1InitialPosition + roll;
                if (position > 10) {
                    position -= 10;
                }
                this.player1Position = position;
                this.player1Score = player1InitialScore + position;
            } else {
                this.player1Position = player1InitialPosition;
                this.player1Score = player1InitialScore;
                int position = player2InitialPosition + roll;
                if (position > 10) {
                    position -= 10;
                }
                this.player2Position = position;
                this.player2Score = player2InitialScore + position;
            }
            this.isPlayer1Turn = !isPlayer1Turn;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Universe universe = (Universe) o;
            return player1Position == universe.player1Position && player2Position == universe.player2Position
                    && player1Score == universe.player1Score && player2Score == universe.player2Score
                    && isPlayer1Turn == universe.isPlayer1Turn;
        }

        @Override
        public int hashCode() {
            return Objects.hash(player1Position, player2Position, player1Score, player2Score, isPlayer1Turn);
        }

        public long getMaxScore() {
            return Math.max(this.player1Score, this.player2Score);
        }

        public long[] score() {
            if (this.player1Score > this.player2Score) {
                return new long[] {1L, 0L};
            } else {
                return new long[] {0L, 1L};
            }
        }
    }
}
