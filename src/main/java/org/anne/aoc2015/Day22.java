package org.anne.aoc2015;

import org.anne.common.Day;

import java.util.*;

public class Day22 extends Day {
    public static void main(String[] args) {
        new Day22().run();
    }

    @Override
    public void execute() {
        setName("Wizard Simulator 20XX");
        List<Integer> input = readFileGetAllInts();
        setPart1(part1(input, 50, 500));
        setPart2(part2(input, 50, 500));
    }

    public static int part1(List<Integer> input, int playerHp, int playerMana) {
        return play(playerHp, playerMana, input.get(0), input.get(1), false);
    }

    public static int part2(List<Integer> input, int playerHp, int playerMana) {
        return play(playerHp, playerMana, input.get(0), input.get(1), true);
    }

    private static int play(int playerHp, int playerMana, int bossHp, int bossDamage, boolean hardMode) {
        PriorityQueue<State> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.totalMana));
        queue.add(new State(playerHp, playerMana, bossHp, 0, 0, 0, 0));
        Set<State> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            State current = queue.poll();
            if (current.bossHp <= 0) {
                return current.totalMana;
            }

            for (State next : nextTurn(current, bossDamage, hardMode)) {
                if (visited.add(next)) {
                    queue.add(next);
                }
            }
        }
        return -1;
    }

    private static List<State> nextTurn(State state, int bossDamage, boolean hardMode) {
        State s = state.playerTurn(hardMode);
        List<State> nextStates = new ArrayList<>();

        for (Spell spell : Spell.values()) {
            if (spell.canCast(s)) {
                nextStates.add(s.launchSpell(spell).bossTurn(bossDamage));
            }
        }
        nextStates.removeIf(r -> r.playerHp <= 0);
        return nextStates;
    }

    record State(int playerHp, int playerMana, int bossHp, int totalMana, int shield, int poison, int recharge) {
        private State launchSpell(Spell spell) {
            return new State(
                    this.playerHp + (spell.equals(Spell.DRAIN) ? 2 : 0),
                    this.playerMana - spell.cost,
                    this.bossHp - spell.damage,
                    this.totalMana + spell.cost,
                    spell.equals(Spell.SHIELD) ? spell.duration : this.shield,
                    spell.equals(Spell.POISON) ? spell.duration : this.poison,
                    spell.equals(Spell.RECHARGE) ? spell.duration : this.recharge
            );
        }

        private State playerTurn(boolean hardMode) {
            int playerHp = this.playerHp, playerMana = this.playerMana, bossHp = this.bossHp, shield = this.shield,
                    poison = this.poison, recharge = this.recharge;
            if (hardMode) {
                playerHp--;
                if (playerHp <= 0) {
                    return new State(playerHp, playerMana, bossHp, this.totalMana, shield, poison, recharge);
                }
            }
            if (recharge > 0) {
                playerMana += 101;
                recharge--;
            }
            if (poison > 0) {
                bossHp -= 3;
                poison--;
            }
            if (shield > 0) {
                shield--;
            }
            return new State(playerHp, playerMana, bossHp, this.totalMana, shield, poison, recharge);
        }

        public State bossTurn(int bossDamage) {
            int bossHp = this.bossHp - (poison > 0 ? 3 : 0);
            int playerHp = this.playerHp - (bossHp > 0 ? Math.max(1, bossDamage - (shield > 0 ? 7 : 0)) : 0);
            return new State(
                    playerHp,
                    playerMana + (recharge > 0 ? 101 : 0),
                    bossHp,
                    totalMana,
                    Math.max(shield - 1, 0),
                    Math.max(poison - 1, 0),
                    Math.max(recharge - 1, 0)
            );
        }
    }

    private enum Spell {
        MAGIC_MISSILE(53, 4, 0) {
            @Override
            public boolean canCast(State state) {
                return state.playerMana >= this.cost;
            }
        },
        DRAIN(73, 2, 0) {
            @Override
            public boolean canCast(State state) {
                return state.playerMana >= this.cost;
            }
        },
        SHIELD(113, 0, 6) {
            @Override
            public boolean canCast(State state) {
                return state.playerMana >= this.cost && state.shield == 0;
            }
        },
        POISON(173, 0, 6) {
            @Override
            public boolean canCast(State state) {
                return state.playerMana >= this.cost && state.poison == 0;
            }
        },
        RECHARGE(229, 0, 5) {
            @Override
            public boolean canCast(State state) {
                return state.playerMana >= this.cost && state.recharge == 0;
            }
        };

        final int cost;
        private final int damage;
        private final int duration;

        public abstract boolean canCast(State state);

        Spell(int cost, int damage, int duration) {
            this.cost = cost;
            this.damage = damage;
            this.duration = duration;
        }
    }
}
