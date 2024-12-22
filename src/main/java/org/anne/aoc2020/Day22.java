package org.anne.aoc2020;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.List;

public class Day22 extends Day {

    public static void main(String[] args) {
        new Day22().run();
    }

    @Override
    public void execute() {
        setName("Crab Combat");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(List<String> input) {
        List<List<Integer>> decks = playerDecks(input);

        return classicGame(decks.get(0), decks.get(1));
    }

    public static int part2(List<String> input) {
        List<List<Integer>> decks = playerDecks(input);
        return recursiveCombat(decks.get(0), decks.get(1), true);
    }

    private static List<List<Integer>> playerDecks(List<String> input) {
        List<Integer> player1Deck = new ArrayList<>();
        List<Integer> player2Deck = new ArrayList<>();

        int index = input.indexOf("");
        for (int i = 1; i < input.size(); i++) {
            if (i < index) {
                player1Deck.add(Integer.valueOf(input.get(i)));
            } else if (i > index + 1) {
                player2Deck.add(Integer.valueOf(input.get(i)));
            }
        }
        return List.of(player1Deck, player2Deck);
    }

    private static int calculateScore(List<Integer> cards) {
        int score = 0;
        for (int i = 0; i < cards.size(); i++) {
            score += (cards.size() - i) * cards.get(i);
        }
        return score;
    }

    private static int recursiveCombat(List<Integer> player1, List<Integer> player2, boolean isFirstGame) {
        int winner;
        List<List<Integer>> player1History = new ArrayList<>();
        List<List<Integer>> player2History = new ArrayList<>();
        while (!player1.isEmpty() && !player2.isEmpty()) {
            if (player1History.contains(player1) && player2History.contains(player2)) {
                return 1;
            } else {
                player1History.add(new ArrayList<>(player1));
                player2History.add(new ArrayList<>(player2));
                int first = player1.get(0);
                int second = player2.get(0);
                player1.remove(0);
                player2.remove(0);
                if (player1.size() >= first && player2.size() >= second) {
                    winner = recursiveCombat(new ArrayList<>(player1.subList(0, first)), new ArrayList<>(player2.subList(0, second)), false);
                    if (winner == 1) {
                        player1.add(first);
                        player1.add(second);
                    } else {
                        player2.add(second);
                        player2.add(first);
                    }
                } else if (first < second) {
                    player2.add(second);
                    player2.add(first);
                } else {
                    player1.add(first);
                    player1.add(second);
                }
            }
        }
        if (isFirstGame) {
            if (!player1.isEmpty()) {
                return calculateScore(player1);
            } else {
                return calculateScore(player2);
            }
        } else {
            if (!player1.isEmpty()) {
                return 1;
            } else {
                return 2;
            }
        }
    }

    private static int classicGame(List<Integer> player1, List<Integer> player2) {
        while (!player1.isEmpty() && !player2.isEmpty()) {
            int first = player1.get(0);
            int second = player2.get(0);
            player1.remove(0);
            player2.remove(0);
            if (first < second) {
                player2.add(second);
                player2.add(first);
            } else {
                player1.add(first);
                player1.add(second);
            }
        }
        if (!player1.isEmpty()) {
            return calculateScore(player1);
        } else {
            return calculateScore(player2);
        }
    }
}
