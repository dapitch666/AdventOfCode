package org.anne.aoc2016;

import org.anne.common.Day;
import org.anne.common.Utils;

import java.util.*;

public class Day10 extends Day {
    public static void main(String[] args) {
        new Day10().run();
    }

    @Override
    public void execute() {
        setName("Balance Bots");
        List<String> input = readFile();
        setPart1(part1(input, 61, 17));
        setPart2(part2(input));
    }

    public static int part1(List<String> input, int higher, int lower) {
        Map<Integer, Bot> bots = getBots(input);
        return getResult(bots, higher, lower);
    }

    public static int part2(List<String> input) {
        Map<Integer, Bot> bots = getBots(input);
        return getResult(bots, 0, 0);
    }

    private static int getResult(Map<Integer, Bot> bots, int higher, int lower) {
        boolean returnHigherAndLower = higher > 0;
        int[] outputBin = new int[30];
        while (bots.values().stream().anyMatch(Bot::has2Ships)) {
            Bot bot = bots.values().stream().filter(Bot::has2Ships).findFirst().orElseThrow();
            if (returnHigherAndLower && bot.hasShips(higher, lower)) {
                return bot.number;
            }
            int low = bot.lower();
            int high = bot.higher();

            if (bot.lowType.equals("bot")) {
                bots.get(bot.lowValue).addShip(low);
            } else {
                outputBin[bot.lowValue] = low;
            }
            if (bot.highType.equals("bot")) {
                bots.get(bot.highValue).addShip(high);
            } else {
                outputBin[bot.highValue] = high;
            }
            bot.chips.clear();
        }
        return outputBin[0] * outputBin[1] * outputBin[2];
    }

    private static Map<Integer, Bot> getBots(List<String> input) {
        Map<Integer, Bot> bots = new HashMap<>();
        for (String line : input) {
            int[] ints = Utils.inputToIntStream(line).toArray();
            int numBot = ints[line.startsWith("value") ? 1 : 0];
            Bot bot = bots.computeIfAbsent(numBot, Bot::new);
            if (line.startsWith("value")) {
                bot.addShip(ints[0]);
            } else {
                bot.addOut(line, ints[1], ints[2]);
            }
        }
        return bots;
    }

    static class Bot {
        int number;
        List<Integer> chips = new ArrayList<>();
        String lowType;
        int lowValue;
        String highType;
        int highValue;

        Bot(int number) {
            this.number = number;
        }

        boolean hasShips(int higher, int lower) {
            return lower() == lower && higher() == higher;
        }

        int lower() {
            return Collections.min(chips);
        }

        int higher() {
            return Collections.max(chips);
        }

        public void addShip(int ship) {
            chips.add(ship);
        }

        public void addOut(String line, int low, int high) {
            String[] parts = line.split(" ");
            this.lowType = parts[5];
            this.lowValue = low;
            this.highType = parts[10];
            this.highValue = high;
        }

        public boolean has2Ships() {
            return chips.size() == 2;
        }
    }
}
