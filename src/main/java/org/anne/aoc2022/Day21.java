package org.anne.aoc2022;

import org.anne.common.Day;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day21 extends Day {
    public static void main(String[] args) {
        Day day = new Day21();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Monkey Math");
        List<String> input = this.readFile();
        this.setPart1(part1(input));
        this.setPart2(part2(input));
        this.printParts();
    }

    static Map<String, Monkey> monkeys = new HashMap<>();

    public static long part1(List<String> input) {
        monkeys = parseInput(input);
        return monkeys.get("root").getValue();
    }

    public static long part2(List<String> input) {
        monkeys = parseInput(input);
        String rootLeft = monkeys.get("root").leftTerm;
        String rootRight = monkeys.get("root").rightTerm;

        String findable = monkeys.get(rootRight).isInHumnBranch() ? rootLeft : rootRight;
        String notFindable = findable.equals(rootLeft) ? rootRight : rootLeft;

        monkeys.values().stream()
                .filter(m -> !m.isInHumnBranch())
                .forEach(Monkey::getValue);

        long myNumber = monkeys.get(findable).getValue();
        Monkey current = monkeys.get(notFindable);

        while(!current.name.equals("humn")) {
            Monkey leftTerm = monkeys.get(current.leftTerm);
            Monkey rightTerm = monkeys.get(current.rightTerm);

            if(leftTerm.isInHumnBranch()) {
                switch (current.operator) {
                    case '+' -> myNumber -= rightTerm.getValue();
                    case '-' -> myNumber += rightTerm.getValue();
                    case '*' -> myNumber /= rightTerm.getValue();
                    case '/' -> myNumber *= rightTerm.getValue();
                }
                current = leftTerm;
            } else {
                switch (current.operator) {
                    case '+' -> myNumber -= leftTerm.getValue();
                    case '-' -> myNumber = leftTerm.getValue() - myNumber;
                    case '*' -> myNumber /= leftTerm.getValue();
                    case '/' -> myNumber *= leftTerm.getValue();
                }
                current = rightTerm;
            }
        }
        return myNumber;
    }



    static class Monkey {
        public final String name;
        public String leftTerm;
        public String rightTerm;
        public char operator;
        public boolean hasValue = false;
        public long value = 0;

        public Monkey(String name, String leftTerm, String rightTerm, char operator) {
            this.name = name;
            this.leftTerm = leftTerm;
            this.rightTerm = rightTerm;
            this.operator = operator;
        }

        public Monkey(String name, long value) {
            this.name = name;
            this.value = value;
            hasValue = true;
        }

        public long getValue() {
            if (!this.hasValue) {
                long leftValue = monkeys.get(this.leftTerm).getValue();
                long rightValue = monkeys.get(this.rightTerm).getValue();
                switch (this.operator) {
                    case '+' -> this.value = leftValue + rightValue;
                    case '-' -> this.value = leftValue - rightValue;
                    case '*' -> this.value = leftValue * rightValue;
                    case '/' -> this.value = leftValue / rightValue;
                }
                this.hasValue = true;
            }
            return this.value;
        }

        boolean isInBranch(String name) {
            if(this.name.equals(name))
                return true;
            if(this.leftTerm == null && this.rightTerm == null)
                return false;
            else
                return monkeys.get(this.leftTerm).isInBranch(name) || monkeys.get(this.rightTerm).isInBranch(name);
        }

        boolean isInHumnBranch() {
            return this.isInBranch("humn");
        }
    }

    private static Map<String, Monkey> parseInput (List<String> input) {
        Map<String, Monkey> ret = new HashMap<>();
        for(String line : input) {
            String[] params = line.split(": | ");
            if(params.length == 2) {
                ret.put(params[0], new Monkey(params[0], Integer.parseInt(params[1])));
            } else {
                ret.put(params[0], new Monkey(params[0], params[1], params[3], params[2].charAt(0)));
            }
        }
        return ret;
    }
}
