package org.anne.aoc2015;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day21 extends Day {
    public static void main(String[] args) {
        new Day21().run();
    }

    @Override
    public void execute() {
        setName("RPG Simulator 20XX");
        List<Integer> input = readFileGetAllInts();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(List<Integer> input) {
        Player boss = new Player(input.get(0), input.get(1), input.get(2));
        return getEquipment().stream()
                .sorted(Comparator.comparingInt(Equipment::cost))
                .filter(e -> new Player(100, e.damage(), e.defense()).wins(boss))
                .mapToInt(Equipment::cost)
                .findFirst()
                .orElseThrow();
    }

    public static int part2(List<Integer> input) {
        Player boss = new Player(input.get(0), input.get(1), input.get(2));
        return getEquipment().stream()
                .sorted(Comparator.comparingInt(Equipment::cost).reversed())
                .filter(e -> boss.wins(new Player(100, e.damage(), e.defense())))
                .mapToInt(Equipment::cost)
                .findFirst()
                .orElseThrow();
    }

    private static List<Equipment> getEquipment() {
        List<Equipment> gear = new ArrayList<>();
        for (Item weapon : WEAPONS) {
            for (Item armor : ARMORS) {
                for (Item ring1 : RINGS) {
                    for (Item ring2 : RINGS) {
                        if (ring1 != ring2 || ring1.name.equals("No ring")) {
                            gear.add(new Equipment(weapon, armor, ring1, ring2));
                        }
                    }
                }
            }
        }
        return gear;
    }

    record Player(int hp, int damage, int defense) {
        boolean wins(Player other) {
            return Math.ceil((double) hp / Math.max(1, other.damage - defense)) >= Math.ceil((double) other.hp / Math.max(1, damage - other.defense));
        }
    }

    record Item(String name, int cost, int damage, int defense) {}

    record Equipment(Item weapon, Item armor, Item ring1, Item ring2) {
        int cost() {
            return weapon.cost + armor.cost + ring1.cost + ring2.cost;
        }
        int damage() {
            return weapon.damage + armor.damage + ring1.damage + ring2.damage;
        }
        int defense() {
            return weapon.defense + armor.defense + ring1.defense + ring2.defense;
        }
    }

    private static final List<Item> WEAPONS = List.of(
            new Item("Dagger", 8, 4, 0),
            new Item("Shortsword", 10, 5, 0),
            new Item("Warhammer", 25, 6, 0),
            new Item("Longsword", 40, 7, 0),
            new Item("Greataxe", 74, 8, 0)
    );

    private static final List<Item> ARMORS = List.of(
            new Item("No armor", 0, 0, 0),
            new Item("Leather", 13, 0, 1),
            new Item("Chainmail", 31, 0, 2),
            new Item("Splintmail", 53, 0, 3),
            new Item("Bandedmail", 75, 0, 4),
            new Item("Platemail", 102, 0, 5)
    );

    private static final List<Item> RINGS = List.of(
            new Item("No ring", 0, 0, 0),
            new Item("Damage +1", 25, 1, 0),
            new Item("Damage +2", 50, 2, 0),
            new Item("Damage +3", 100, 3, 0),
            new Item("Defense +1", 20, 0, 1),
            new Item("Defense +2", 40, 0, 2),
            new Item("Defense +3", 80, 0, 3)
    );
}
