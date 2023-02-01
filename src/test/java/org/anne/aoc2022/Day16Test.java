package org.anne.aoc2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day16Test {
    List<String> input = Arrays.asList(
            "Valve AA has flow rate=0; tunnels lead to valves DD, II, BB",
            "Valve BB has flow rate=13; tunnels lead to valves CC, AA",
            "Valve CC has flow rate=2; tunnels lead to valves DD, BB",
            "Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE",
            "Valve EE has flow rate=3; tunnels lead to valves FF, DD",
            "Valve FF has flow rate=0; tunnels lead to valves EE, GG",
            "Valve GG has flow rate=0; tunnels lead to valves FF, HH",
            "Valve HH has flow rate=22; tunnel leads to valve GG",
            "Valve II has flow rate=0; tunnels lead to valves AA, JJ",
            "Valve JJ has flow rate=21; tunnel leads to valve II"
    );

    @Test
    void part1() {
        var valves = Day16.parseInput(input);
        var paths = Day16.getPaths(valves);
        var maxPressureGrid = Day16.getAllPressures(valves, paths);
        assertEquals(1651, Day16.part1(maxPressureGrid));
    }

    @Test
    void part2() {
        var valves = Day16.parseInput(input);
        var paths = Day16.getPaths(valves);
        var maxPressureGrid = Day16.getAllPressures(valves, paths);
        assertEquals(1707, Day16.part2(maxPressureGrid));
    }
}