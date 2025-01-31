package org.anne.aoc2017;

import org.anne.common.Day;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static org.anne.common.Utils.last;

public class Day25 extends Day {
    public static void main(String[] args) {
        new Day25().run();
    }

    @Override
    public void execute() {
        setName("The Halting Problem");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2());
        printParts();
    }

    public static int part1(List<String> input) {
        String currentState = input.get(0).split(" ")[3].substring(0, 1);
        int steps = Integer.parseInt(input.get(1).replaceAll("\\D", ""));
        List<State> states = getStates(input);

        Map<Integer, Integer> tape = new HashMap<>();
        int position = 0;

        for (int i = 0; i < steps; i++) {
            State state = states.get("ABCDEF".indexOf(currentState));
            int currentValue = tape.getOrDefault(position, 0);
            Rule action = state.rules.get(currentValue);
            tape.put(position, action.write);
            position += action.move;
            currentState = action.nextState;
        }
        return (int) tape.values().stream().filter(value -> value == 1).count();
    }

    public static String part2() {
        return "Merry Christmas!";
    }

    private static List<State> getStates(List<String> input) {
        List<State> states = new ArrayList<>();

        for (int i = 3; i < input.size(); i += 10) {
            String name = input.get(i).split(" ")[2].substring(0, 1);
            Map<Integer, Rule> actions = new HashMap<>();
            for (int j = 0; j < 2; j++) {
                int write = input.get(i + 2 + (j * 4)).contains("1") ? 1 : 0;
                int move = input.get(i + 3 + j * 4).contains("right") ? 1 : -1;
                String nextState = last(input.get(i + 4 + (j * 4)).split(" ")).substring(0, 1);
                actions.put(j, new Rule(write, move, nextState));
            }
            states.add(new State(name, actions));
        }
        return states;
    }

    record State(String name, Map<Integer, Rule> rules) {
    }

    record Rule(int write, int move, String nextState) {
    }
}