package org.anne.aoc2022;

import org.anne.common.Day;
import org.anne.common.Utils;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

public class Day11 extends Day {
    public static void main(String[] args) {
        Day day = new Day11();
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    public static long part1(List<String> input) {
        return getResultAfterRound(20, true, input);
    }

    private static long getResultAfterRound(int nbRounds, boolean isFirstPart, List<String> input) {
        List<Monkey> monkeys = new ArrayList<>();
        for (int i = 0; i < input.size(); i += 7) {
            monkeys.add(new Monkey(input.subList(i, i+6)));
        }
        long pgdc = monkeys.stream().mapToLong(Monkey::getTest).reduce((a,b) -> a * b).orElseThrow();
        for (int i = 0; i < nbRounds; i++) {
            for (Monkey monkey : monkeys) {
                while (monkey.items.size() > 0) {
                    monkey.nbInspections++;
                    long item = monkey.items.remove(0);
                    String[] operation = monkey.operation;
                    long secondTerm;
                    if (operation[1].equals("old")) {
                        secondTerm = item;
                    } else {
                        secondTerm = Integer.parseInt(operation[1]);
                    }
                    switch (operation[0]) {
                        case "*" -> item = item * secondTerm;
                        case "+" -> item = item + secondTerm;
                    }
                    if (isFirstPart) item /= 3;
                    monkeys.get(item % monkey.test == 0 ? monkey.ifTrue : monkey.ifFalse).items.add(item % pgdc);
                }
            }
        }
        long[] inspections = monkeys.stream().mapToLong(Monkey::getNbInspections).sorted().toArray();
        return inspections[inspections.length - 1] * inspections[inspections.length - 2];
    }

    public static long part2(List<String> input) {
        return getResultAfterRound(10000, false, input);
    }


    public static class Monkey {
        protected final List<Long> items;
        private final String[] operation;
        private int test;
        private int ifTrue;
        private int ifFalse;
        private long nbInspections = 0;


        public Monkey(List<String> input) {
            this.items = new ArrayList<>();
            this.operation = new String[2];
            for (String line : input) {
                String[] params = line.split(" ");
                switch (params[0]) {
                    case "Starting" -> {
                        for (int i = 2; i < params.length; i++) {
                            this.items.add(Long.parseLong(params[i].replace(",", "")));
                        }
                    }
                    case "Operation:" -> {
                        this.operation[0] = params[4];
                        this.operation[1] = params[5];
                    }
                    case "Test:" -> this.test = Integer.parseInt(params[3]);
                    case "If" -> {
                        int value = Integer.parseInt(params[5]);
                        if (params[1].equals("true:")) {
                            this.ifTrue = value;
                        } else {
                            this.ifFalse = value;
                        }
                    }
                }
            }
        }

        public long getNbInspections() {
            return nbInspections;
        }

        public int getTest() {
            return test;
        }
    }
}
