package org.anne.aoc2023;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day4 extends Day {
    public static void main(String[] args) {
        Day day = new Day4();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Scratchcards");
        List<String> input = this.readFile();
        this.setPart1(part1(input));
        this.setPart2(part2(input));
        this.printParts();
    }


    public static long part1(List<String> input) {
        return input.stream().map(Day4::getCard).mapToLong(Card::points).sum();
    }

    public static int part2(List<String> input) {
        var cards = input.stream().map(Day4::getCard).toList();
        var totalCards = new ArrayList<Card>();
        cards.forEach(card -> processCard(card, cards, totalCards));
        return totalCards.size();
    }

    record Card(int id, int winningNumberCount) {
        int points() {
            return (int) Math.pow(2, winningNumberCount - 1);
        }
    }
    
    private static Card getCard(String line) {
        var id = Integer.parseInt(line.split(":")[0].split("\\s+")[1]);
        var winningNumbers = Arrays.stream(
                line.split(":")[1].split("\\|")[0].split(" "))
                .filter(x -> !x.isEmpty()).map(Integer::parseInt).collect(Collectors.toSet());
        var myNumbers = Arrays.stream(
                line.split(":")[1].split("\\|")[1].split(" "))
                .filter(x -> !x.isEmpty()).map(Integer::parseInt).collect(Collectors.toSet());
        return new Card(id, (int) myNumbers.stream().filter(winningNumbers::contains).count());
    }

    private static void processCard(Card card, List<Card> cards, ArrayList<Card> totalCards) {
        totalCards.add(card);
        for (int i = 0; i < card.winningNumberCount; i++) {
            processCard(cards.get(card.id + i), cards, totalCards);
        }
    }
}
