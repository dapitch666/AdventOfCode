package org.anne.aoc2019;

import org.anne.common.Point3d;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Day24Test {
    List<String> input = List.of(
            "....#",
            "#..#.",
            "#..##",
            "...#.",
            "#...."
    );
    
    @Test
    void testPart1() {
        assertEquals(2129920, Day24.part1(input));
    }
    
    @Test
    void testPart2() {
        assertEquals(99, Day24.part2(input, 10));
    }
    
    @Test
    void testGetBiodiversity() {
        assertEquals(2129920, Day24.getBiodiversity(new HashSet<>(List.of(
                new Point(0, 3),
                new Point(1, 4)
        ))));
    }
    
    @Test
    void testGetNeighbours() {
        Point3d point = new Point3d(3, 2, 0);
        List<Point3d> expected = List.of(
                new Point3d(3, 1, 0),
                new Point3d(3, 3, 0),
                new Point3d(4, 2, 0),
                new Point3d(4, 0, 1),
                new Point3d(4, 1, 1),
                new Point3d(4, 2, 1),
                new Point3d(4, 3, 1),
                new Point3d(4, 4, 1)
        );
        List<Point3d> actual = Day24.getNeighbours(point);
        assertTrue(actual.containsAll(expected));
    }
    
    @Test
    void testGetNeighbours2() {
        Point3d point = new Point3d(4, 0, 0);
        List<Point3d> expected = List.of(
                new Point3d(3, 0, 0),
                new Point3d(4, 1, 0),
                new Point3d(2, 1, -1),
                new Point3d(3, 2, -1)
        );
        List<Point3d> actual = Day24.getNeighbours(point);
        assertTrue(actual.containsAll(expected));
    }
}
