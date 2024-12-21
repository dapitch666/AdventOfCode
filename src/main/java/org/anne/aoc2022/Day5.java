package org.anne.aoc2022;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day5 extends Day {
    public static void main(String[] args) {
        Day day = new Day5();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Supply Stacks");
        List<String> input = this.readFile();
        this.setPart1(part1(input));
        this.setPart2(part2(input));
        this.printParts();
    }

    public static String part1(List<String> input) {
        int indexOfBlank = input.indexOf("");
        int stackNb = Integer.parseInt(input.get(indexOfBlank - 1).substring(input.get(indexOfBlank - 1).length() - 1));
        var stacks = getStacks(input.subList(0, indexOfBlank - 1), stackNb);
        var moves = getMoves(input.subList(indexOfBlank + 1, input.size()));

        for (int[] move : moves) {
            for (int i = 0; i < move[0]; i++) {
                stacks.get(move[2]-1).push(stacks.get(move[1]-1).pop());
            }
        }
        return stacks.stream().map(Stack::pop).map(Object::toString).collect(Collectors.joining());
    }

    public static String part2(List<String> input) {
        int indexOfBlank = input.indexOf("");
        int stackNb = Integer.parseInt(input.get(indexOfBlank - 1).substring(input.get(indexOfBlank - 1).length() - 1));
        var stacks = getStacks(input.subList(0, indexOfBlank - 1), stackNb);
        var moves = getMoves(input.subList(indexOfBlank + 1, input.size()));

        for (int[] move : moves) {
            Stack<Character> tmpStack = new Stack<>();
            for (int i = 0; i < move[0]; i++) {
                tmpStack.push(stacks.get(move[1]-1).pop());
            }
            for (int i = 0; i < move[0]; i++) {
                stacks.get(move[2]-1).push(tmpStack.pop());
            }
        }
        return stacks.stream().map(Stack::pop).map(Object::toString).collect(Collectors.joining());
    }

    static List<int[]> getMoves(List<String> input) {
        Pattern p = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");
        List<int[]> moves = new ArrayList<>();
        for (String line : input) {
            Matcher m = p.matcher(line);
            if(m.matches()) {
                moves.add(new int[]{
                        Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3))});
            }
        }
        return moves;
    }

    static List<Stack<Character>> getStacks(List<String> input, int stackNb) {
        List<Stack<Character>> stacks = new ArrayList<>();
        for (int j = 1; j <= stackNb * 4; j += 4) {
            Stack<Character> stack = new Stack<>();
            for (int i = input.size(); i >= 0; i--) {
                try {
                    char crate = input.get(i).charAt(j);
                    if (!(crate == ' ')) {
                        stack.push(crate);
                    }
                } catch (IndexOutOfBoundsException ignored) {}
            }
            stacks.add(stack);
        }
        return stacks;
    }
}
