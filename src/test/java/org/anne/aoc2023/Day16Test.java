package org.anne.aoc2023;

import org.anne.common.Direction;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day16Test {

    List<String> input = Arrays.asList(
            ".|...\\....",
            "|.-.\\.....",
            ".....|-...",
            "........|.",
            "..........",
            ".........\\",
            "..../.\\\\..",
            ".-.-/..|..",
            ".|....-|.\\",
            "..//.|...."
    );
    
    @Test
    void testPart1() {
        assertEquals(46, Day16.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(51, Day16.part2(input));
    }
    
    @Test
    void testRotate() {
        Day16.Beam beam = new Day16.Beam(new Point(0, 0), Direction.EAST);
        assertEquals(Direction.NORTH, Direction.rotate90(beam.direction(), false));
    }
 }
