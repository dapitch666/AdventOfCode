package org.anne.aoc2022;

import org.anne.common.Day;
import org.anne.common.Utils;

import java.util.List;

public class Day19 extends Day {
    public static void main(String[] args) {
        Day day = new Day19();
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    static int best;

    public static int part1(List<String> input) {
        int result = 0;
        for (String line : input) {
            best = 0;
            var blueprint = new Blueprint(Utils.inputToIntStream(line).toArray());
            var openGeodes = openGeodes(blueprint, 0, 0, 0, 0, 1, 0, 0, 0, 24);
            result += blueprint.number * openGeodes;
        }
        return result;
    }

    public static int part2(List<String> input) {
        int result = 1;
        for (String line : input.subList(0, Math.min(3, input.size()))) {
            best = 0;
            var blueprint = new Blueprint(Utils.inputToIntStream(line).toArray());
            var openGeodes = openGeodes(blueprint, 0, 0, 0, 0, 1, 0, 0, 0, 32);
            result *= openGeodes;
        }
        return result;
    }

    private record Blueprint (int number, int oreCostForOreBot, int oreCostForClayBot, int oreCostForObsidianBot,
                              int clayCostForObsidianBot, int oreCostForGeodeBot, int obsidianCostForGeodeBot) {
        public Blueprint(int[] bp) {
            this(bp[0], bp[1], bp[2], bp[3], bp[4], bp[5], bp[6]);
        }
    }

    private static int openGeodes(Blueprint blueprint, int ore, int clay, int obsidian, int geodes, int oreBots, int clayBots, int obsidianBots, int geodeBots, int timeLeft) {
        if (timeLeft == 0) {
            return geodes;
        }

        if (geodes + (geodeBots * timeLeft) + (timeLeft * (timeLeft - 1) / 2) < best) {
            return 0;
        }

        var newOre = ore + oreBots;
        var newClay = clay + clayBots;
        var newObsidian = obsidian + obsidianBots;
        var newGeode = geodes + geodeBots;
        timeLeft--;

        if (ore >= blueprint.oreCostForGeodeBot && obsidian >= blueprint.obsidianCostForGeodeBot()) {
            return openGeodes(blueprint,
                    newOre - blueprint.oreCostForGeodeBot,
                    newClay,
                    newObsidian - blueprint.obsidianCostForGeodeBot,
                    newGeode,
                    oreBots,
                    clayBots,
                    obsidianBots,
                    geodeBots + 1,
                    timeLeft);
        }
        if (clayBots >= blueprint.clayCostForObsidianBot &&
                obsidianBots < blueprint.obsidianCostForGeodeBot &&
                ore >= blueprint.oreCostForObsidianBot &&
                clay >= blueprint.clayCostForObsidianBot) {
            return openGeodes(blueprint,
                    newOre - blueprint.oreCostForObsidianBot,
                    newClay - blueprint.clayCostForObsidianBot,
                    newObsidian,
                    newGeode,
                    oreBots,
                    clayBots,
                    obsidianBots + 1,
                    geodeBots,
                    timeLeft);
        }

        if (obsidianBots < blueprint.obsidianCostForGeodeBot &&
                ore >= blueprint.oreCostForObsidianBot &&
                clay >= blueprint.clayCostForObsidianBot) {
            best = Math.max(best, openGeodes(blueprint,
                    newOre - blueprint.oreCostForObsidianBot,
                    newClay - blueprint.clayCostForObsidianBot,
                    newObsidian,
                    newGeode,
                    oreBots,
                    clayBots,
                    obsidianBots + 1,
                    geodeBots,
                    timeLeft));
        }
        if (clayBots < blueprint.clayCostForObsidianBot && ore >= blueprint.oreCostForClayBot) {
            best = Math.max(best, openGeodes(blueprint,
                    newOre - blueprint.oreCostForClayBot,
                    newClay,
                    newObsidian,
                    newGeode,
                    oreBots,
                    clayBots + 1,
                    obsidianBots,
                    geodeBots,
                    timeLeft));
        }
        if (oreBots < 4 && ore >= blueprint.oreCostForOreBot) {
            best = Math.max(best, openGeodes(blueprint,
                    newOre - blueprint.oreCostForOreBot,
                    newClay,
                    newObsidian,
                    newGeode,
                    oreBots + 1,
                    clayBots,
                    obsidianBots,
                    geodeBots,
                    timeLeft));
        }
        if (ore <= 4) {
            best = Math.max(best, openGeodes(blueprint,
                    newOre,
                    newClay,
                    newObsidian,
                    newGeode,
                    oreBots,
                    clayBots,
                    obsidianBots,
                    geodeBots,
                    timeLeft));
        }
        return best;
    }
}
