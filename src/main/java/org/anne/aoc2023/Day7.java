package org.anne.aoc2023;

import org.anne.common.Day;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day7 extends Day {
    public static void main(String[] args) {
        Day day = new Day7();
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }


    public static long part1(List<String> input) {
        long result = 0;
        List<Hand> hands = input
                .stream()
                .map(line -> line.split(" "))
                .map(parts -> new Hand(parts[0], Integer.parseInt(parts[1]), false))
                .sorted()
                .toList();
        for (int i = 0; i < hands.size(); i++) {
            result += (long) hands.get(i).bid * (i + 1);
        }
        return result;
    }

    public static long part2(List<String> input) {
        long result = 0;
        List<Hand> hands = input
                .stream()
                .map(line -> line.split(" "))
                .map(parts -> new Hand(parts[0], Integer.parseInt(parts[1]), true))
                .sorted()
                .toList();
        for (int i = 0; i < hands.size(); i++) {
            result += (long) hands.get(i).bid * (i + 1);
        }
        return result;
    }
    
    public record Hand(String cards, int bid, boolean isPart2) implements Comparable<Hand> {
        public Hand(String cards, int bid, boolean isPart2) {
            this.cards = cards
                    .replaceAll("A", "E")
                    .replaceAll("T", "A")
                    .replaceAll("J", "B")
                    .replaceAll("Q", "C")
                    .replaceAll("K", "D");
            this.bid = bid;
            this.isPart2 = isPart2;
        }

        @Override
        public int compareTo(@NotNull Hand other) {
            if (type() != other.type()) {
                return Integer.compare(other.type(), type());
            } else {
                if (isPart2) {
                    return String.CASE_INSENSITIVE_ORDER.compare(
                            cards.replaceAll("B", "1"),
                            other.cards.replaceAll("B", "1"));
                }
                return String.CASE_INSENSITIVE_ORDER.compare(cards, other.cards);
            }
        }
        
        int type() {
            Map<Character, Integer> frequency =
                    cards.chars()
                            .mapToObj(c -> (char)c)
                            .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(c -> 1)));
            
            // In part 2, we add the joker cards to the highest frequency card
            if (isPart2 && frequency.containsKey('B')) {
                // Five of a kind, where all five cards are joker
                if (frequency.size() == 1) {
                    return 0;
                }
                int numberOfJokers = frequency.get('B');
                frequency.remove('B');
                char highestFrequencyCard = Collections.max(frequency.entrySet(), Map.Entry.comparingByValue()).getKey();
                frequency.merge(highestFrequencyCard, numberOfJokers, Integer::sum);
            }

            // Five of a kind, where all five cards have the same label: AAAAA
            if (frequency.size() == 1) {
                return 0;
            }
            
            if (frequency.size() == 2) {
                // Four of a kind, where four cards have the same label and one card has a different label: AA8AA
                if (frequency.containsValue(4)) {
                    return 1;
                }
                // Full house, where three cards have the same label, and the remaining two cards share a different label: 23332
                return 2;
            }
            
            if (frequency.size() == 3) {
                // Three of a kind, where three cards have the same label, and the remaining two cards are each different from any other card in the hand: TTT98
                if (frequency.containsValue(3)) {
                    return 3;
                }
                // Two pair, where two cards share one label, two other cards share a second label, and the remaining card has a third label: 23432
                return 4;
            }
            
            if (frequency.size() == 4) {
                // One pair, where two cards share one label, and the other three cards have a different label from the pair and each other: A23A4
                return 5;
            }
            
            return 6;
        }
    }
}

