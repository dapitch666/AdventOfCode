package org.anne.aoc2018;

import org.anne.common.Day;
import org.anne.common.Utils;

import java.util.*;
import java.util.regex.Pattern;

public class Day24 extends Day {
    public static void main(String[] args) {
        new Day24().run();
    }

    @Override
    public void execute() {
        setName("Immune System Simulator 20XX");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(List<String> input) {
        List<Group> groups = getGroups(input);
        while (combatContinue(groups)) {
            fight(groups);
        }
        return getResult(groups);
    }

    public static int part2(List<String> input) {
        List<Group> groups = getGroups(input);
        while (true) {
            groups.forEach(Group::resetAndBoost);
            while (combatContinue(groups)) {
                if (!fight(groups)) break;
            }
            if (immuneSystemWins(groups)) {
                return getResult(groups);
            }
        }
    }

    private static boolean fight(List<Group> groups) {
        groups.sort(Comparator.comparingInt(Group::effectivePower).thenComparingInt(g -> g.initiative).reversed());
        Map<Group, Group> opponents = new HashMap<>();
        for (Group group : groups) {
            if (!group.isAlive()) continue;
            groups.stream()
                    .filter(g -> g.isSuitableTarget(group) && !opponents.containsValue(g))
                    .max(Comparator.comparingInt(group::actualDamage)
                            .thenComparingInt(Group::effectivePower)
                            .thenComparingInt(g -> g.initiative))
                    .ifPresent(target -> opponents.put(group, target));
        }
        int totalKilled = opponents.keySet().stream()
                .filter(Group::isAlive)
                .sorted(Comparator.comparingInt((Group attacker) -> attacker.initiative).reversed())
                .mapToInt(attacker -> {
                    Group defender = opponents.get(attacker);
                    int killed = Math.min(attacker.actualDamage(defender) / defender.hp, defender.units);
                    defender.units -= killed;
                    return killed;
                }).sum();
        return totalKilled > 0;
    }

    private static boolean combatContinue(List<Group> groups) {
        return groups.stream().filter(Group::isAlive).map(group -> group.army).distinct().count() > 1;
    }

    private static int getResult(List<Group> groups) {
        return groups.stream().filter(Group::isAlive).mapToInt(group -> group.units).sum();
    }

    private static boolean immuneSystemWins(List<Group> groups) {
        return groups.stream().filter(group -> group.army.equals("Infection")).noneMatch(Group::isAlive);
    }

    private static List<Group> getGroups(List<String> input) {
        List<Group> groups = new ArrayList<>();
        String army = "";
        for (String line : input) {
            if (line.endsWith(":")) {
                army = line.substring(0, line.length() - 1);
            } else if (!line.isEmpty()) {
                groups.add(new Group(army, line));
            }
        }
        return groups;
    }

    private static class Group {
        final String army;
        int units;
        final int initialUnits;
        final int hp;
        List<String> weaknesses = new ArrayList<>();
        List<String> immunities = new ArrayList<>();
        int damage;
        final String damageType;
        final int initiative;

        public Group(String army, String string) {
            Pattern pattern = Pattern.compile("([0-9]+) units each with ([0-9]+) hit points (\\(.*\\))? ?with an attack that does ([0-9]+) ([a-z]+) damage at initiative ([0-9]+)");
            List<String> match = Utils.match(pattern, string);
            this.army = army;
            this.initialUnits = Integer.parseInt(match.get(0));
            this.units = initialUnits;
            this.hp = Integer.parseInt(match.get(1));
            if (match.get(2) != null) {
                for (String s : match.get(2).replace("(", "").replace(")", "").split("; ")) {
                    if (s.startsWith("weak")) {
                        this.weaknesses = Arrays.asList(s.substring(8).split(", "));
                    } else if (s.startsWith("immune")) {
                        this.immunities = Arrays.asList(s.substring(10).split(", "));
                    }
                }
            }
            this.damage = Integer.parseInt(match.get(3));
            this.damageType = match.get(4);
            this.initiative = Integer.parseInt(match.get(5));
        }

        public int effectivePower() {
            return units * damage;
        }

        public int actualDamage(Group defender) {
            if (defender.immunities.contains(damageType)) return 0;
            int damage = effectivePower();
            if (defender.weaknesses.contains(damageType)) damage *= 2;
            return damage;
        }

        public boolean isAlive() {
            return units > 0;
        }

        public boolean isSuitableTarget(Group attacker) {
            return !army.equals(attacker.army) && units > 0 && !immunities.contains(attacker.damageType);
        }

        public void resetAndBoost() {
            units = initialUnits;
            if (army.equals("Immune System")) {
                damage ++;
            }
        }
    }
}
