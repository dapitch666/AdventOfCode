package org.anne.aoc2022;

import org.anne.common.Day;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Day20 extends Day {
    public static void main(String[] args) {
        Day day = new Day20();
        day.run();
    }

    @Override
    public void execute() {
        this.setName("Grove Positioning System");
        List<Integer> input = this.readFileAsInts();
        this.setPart1(part1(input));
        this.setPart2(part2(input));
        this.printParts();
    }

    public static long part1(List<Integer> input) {
        List<Num> circular = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            circular.add(new Num(i, input.get(i)));
        }
        return mix(circular, 1);
    }

    public static long part2(List<Integer> input) {
        List<Num> circular = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            circular.add(new Num(i, input.get(i) * 811589153L));
        }
        return mix(circular, 10);
    }

    private static long mix (List<Num> circular, int iter) {
        int modulo = circular.size();
        for (int j = 0; j < iter; j++) {
            for (int i = 0; i < modulo; i++) {
                Move toMove = getMove(circular, i);
                circular.remove(toMove.idx);
                int newIdx = toMove.newIndex(modulo - 1);
                circular.add(newIdx, toMove.num);
            }
        }

        int zeroIdx = IntStream.range(0, modulo)
                .filter(i -> circular.get(i).value == 0)
                .findFirst()
                .orElseThrow();

        return circular.get((zeroIdx + 1000) % modulo).value
                + circular.get((zeroIdx + 2000) % modulo).value
                + circular.get((zeroIdx + 3000) % modulo).value;
    }

    record Num (int id, long value) {}
    record Move (int idx, Num num) {
        int newIndex (int modulo) {
            return (int) (modulo + this.idx + this.num.value % modulo) % modulo;
        }
    }

    static Move getMove (List<Num> circular, int val) {
        return IntStream.range(0, circular.size())
                .filter(i -> circular.get(i).id == val)
                .mapToObj(i -> new Move(i, circular.get(i)))
                .findFirst()
                .orElseThrow();
    }
}