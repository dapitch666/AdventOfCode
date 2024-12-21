package org.anne.aoc2023;

import org.anne.common.Day;
import org.anne.common.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day9 extends Day {
    public static void main(String[] args) {
        Day day = new Day9();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Mirage Maintenance");
        List<String> input = this.readFile();
        this.setPart1(part1(input));
        this.setPart2(part2(input));
        this.printParts();
    }


    public static int part1(List<String> input) {
        return getPrediction(input, false);
    }

    public static int part2(List<String> input) {
        return getPrediction(input, true);
    }
  
    static int getPrediction (List<String> input, boolean reverse) {
        int result = 0;
        for (String line : input) {
            int prediction = 0;
            List<Integer> history = Utils.inputToIntStream(line)
                    .boxed()
                    .toList();
            List<List<Integer>> sequences = new ArrayList<>();
            sequences.add(history);
            while (!sequences.get(sequences.size() - 1).stream().allMatch(x -> x == 0)) {
                List<Integer> lastSequence = sequences.get(sequences.size() - 1);
                List<Integer> newSequence = IntStream.range(1, lastSequence.size())
                        .mapToObj(i -> lastSequence.get(i) - lastSequence.get(i - 1))
                        .collect(Collectors.toList());
                sequences.add(newSequence);
            }
            for (int i = sequences.size() - 2; i >= 0; i--) {
                if (reverse) {
                    prediction = sequences.get(i).get(0) - prediction;
                } else {
                    prediction += sequences.get(i).get(sequences.get(i).size() - 1);
                }
            }
            result += prediction;
        }
        return result;
    }
}
