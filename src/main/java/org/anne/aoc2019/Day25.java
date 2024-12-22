package org.anne.aoc2019;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.anne.common.Constants.LINE_SEPARATOR;

public class Day25 extends Day {
    public static void main(String[] args) {
        new Day25().run();
    }

    @Override
    public void execute() {
        setName("Cryostasis");
        String input = readFileOneLine();
        setPart1(part1(input));
        setPart2(part2());
        printParts();
    }

    public static String part1(String input) {
        Computer computer = new Computer(input);
        String goToCheckPointWithAllObjects = """
                east
                take spool of cat6
                north
                north
                take hypercube
                south
                south
                west
                south
                east
                take space heater
                west
                south
                south
                east
                east
                take planetoid
                west
                west
                north
                north
                north
                north
                take festive hat
                west
                take dark matter
                north
                east
                take semiconductor
                east
                take sand
                north
                """;
        StringBuilder program = new StringBuilder(goToCheckPointWithAllObjects + "inv" + LINE_SEPARATOR);
        List<String> output = Arrays.stream(computer.computeAndGetOutputAsString(program.toString()).split(LINE_SEPARATOR)).toList();
        List<String> objects = new ArrayList<>();
        List<List<String>> combinations = new ArrayList<>();
        program = new StringBuilder();
        for (int i = output.indexOf("Items in your inventory:") + 1; i < output.size(); i++) {
            var object = output.get(i);
            if (object.isEmpty()) {
                break;
            }
            object = object.substring(2);
            objects.add(object);
            program.append("drop ").append(output.get(i).substring(2)).append(LINE_SEPARATOR);
        }
        computer.compute(program.toString());
        generateCombinations(objects, new ArrayList<>(), 0, combinations);
        for (List<String> combination : combinations) {
            program = new StringBuilder();
            for (String object : combination) {
                program.append("take ").append(object).append(LINE_SEPARATOR);
            }
            program.append("west").append(LINE_SEPARATOR);
            String out = computer.computeAndGetOutputAsString(program.toString());
            program = new StringBuilder();
            if (out.contains("Analysis complete! You may proceed.")) {
                return Arrays.stream(out.split(LINE_SEPARATOR))
                        .filter(s -> s.contains("You should be able to get in by typing"))
                        .findFirst()
                        .orElseThrow()
                        .replaceAll(".*?(\\d+).*", "$1");
            } else {
                for (String object : combination) {
                    program.append("drop ").append(object).append(LINE_SEPARATOR);
                }
                computer.compute(program.toString());
            }
        }
        return "";
    }

    @SuppressWarnings("SameReturnValue")
    public static String part2() {
        return "Merry Christmas!";
    }

    public static void generateCombinations(List<String> list, List<String> temp, int start, List<List<String>> result) {
        if (start == list.size()) {
            result.add(new ArrayList<>(temp));
            return;
        }
        temp.add(list.get(start));
        generateCombinations(list, temp, start + 1, result);
        temp.remove(temp.size() - 1);
        generateCombinations(list, temp, start + 1, result);
    }
}