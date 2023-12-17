package org.anne.aoc2023;

import org.anne.common.Direction;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Day17Test {

    List<String> input = Arrays.asList(
            "2413432311323",
            "3215453535623",
            "3255245654254",
            "3446585845452",
            "4546657867536",
            "1438598798454",
            "4457876987766",
            "3637877979653",
            "4654967986887",
            "4564679986453",
            "1224686865563",
            "2546548887735",
            "4322674655533"
    );
    
    List<String> input2 = Arrays.asList(
            "111111111111",
            "999999999991",
            "999999999991",
            "999999999991",
            "999999999991"
    );
    
    @Test
    void testPart1() {
        assertEquals(102, Day17.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(94, Day17.part2(input));
        assertEquals(71, Day17.part2(input2));
    }
    
    @Test
    void testPriorityQueue() {
        var pq = new PriorityQueue<Day17.Status>();
        pq.add(new Day17.Status(new Point(0, 0), null, 0, 100));
        pq.add(new Day17.Status(new Point(4, 2), Direction.NORTH, 0, 0));
        pq.add(new Day17.Status(new Point(5, 0), Direction.NORTH, 0, 25));
        assertEquals(0, Objects.requireNonNull(pq.poll()).heatLoss());
        assertEquals(25, Objects.requireNonNull(pq.poll()).heatLoss());
        var seen = new HashSet<Day17.Status>();
        seen.add(new Day17.Status(new Point(0, 0), Direction.NORTH, 1, 0));
        assertTrue(seen.contains(new Day17.Status(new Point(0, 0), Direction.NORTH, 1, 200)));
        assertFalse(seen.contains(new Day17.Status(new Point(1, 0), Direction.NORTH, 1, 0)));
        seen.add(new Day17.Status(new Point(0, 0), Direction.NORTH, 1, 10));
        assertEquals(1, seen.size());
    }
 }
