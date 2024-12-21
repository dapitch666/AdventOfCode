package org.anne.aoc2019;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14 extends Day {
    static List<Reaction> reactions = new ArrayList<>();

    public static void main(String[] args) {
        Day day = new Day14();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Space Stoichiometry");
        List<String> input = this.readFile();
        this.setPart1(part1(input));
        this.setPart2(part2(input));
        this.printParts();
    }

    static long part1(List<String> input) {
        reactions = input.stream().map(Day14::parseReaction).toList();
        return getOre(new Chemical("FUEL", 1), new HashMap<>());
    }

    static long part2(List<String> input) {
        reactions = input.stream().map(Day14::parseReaction).toList();
        long ore = 1000000000000L;
        long fuel = 1;
        while (true) {
            long oreNeeded = getOre(new Chemical("FUEL", fuel + 1), new HashMap<>());
            if (oreNeeded > ore) {
                return fuel;
            } else {
                fuel = Math.max(fuel + 1, (fuel + 1) * ore / oreNeeded);
            }
        }
    }

    static long getOre(Chemical chemical, Map<String, Long> leftovers) {
        if (chemical.name.equals("ORE")) {
            return chemical.amount;
        }
        long left = leftovers.getOrDefault(chemical.name, 0L);
        if (leftovers.containsKey(chemical.name) && chemical.amount <= left) {
            leftovers.put(chemical.name, left - chemical.amount);
            return 0;
        }
        long amount = chemical.amount - left;
        leftovers.put(chemical.name, 0L);
        Reaction reaction = reactions.stream().filter(r -> r.output.name.equals(chemical.name)).findFirst().orElseThrow();
        long times = (long) Math.ceil((double) amount / reaction.output.amount);
        long total = 0;
        for (Chemical c : reaction.inputs) {
            total += getOre(new Chemical(c.name, c.amount * times), leftovers);
        }
        leftovers.put(chemical.name, reaction.output.amount * times - amount);
        return total;
    }

    static Reaction parseReaction(String input) {
        String[] parts = input.split(" => ");
        String[] in = parts[0].split(", ");
        String[] out = parts[1].split(" ");
        List<Chemical> inputs = new ArrayList<>();
        for (String s : in) {
            String[] inputParts = s.split(" ");
            inputs.add(new Chemical(inputParts[1], Long.parseLong(inputParts[0])));
        }
        Chemical output = new Chemical(out[1], Long.parseLong(out[0]));
        return new Reaction(inputs, output);
    }
    record Reaction(List<Chemical> inputs, Chemical output) {}

    record Chemical(String name, long amount) {}
}
