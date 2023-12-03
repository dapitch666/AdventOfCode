package org.anne.aoc2023;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class Day3Test {

    List<String> input = Arrays.asList(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...$.*....",
            ".664.598.."
    );
    
    List<String> input2 = Arrays.asList(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".+...+..58",
            "592.......",
            "......755.",
            "...$.*....",
            ".664.598.."
    );
    
  
    @Test
    void testPart1() {
        assertEquals(4361, Day3.part1(input));
        assertEquals(4361, Day3.part1(input2));
    }

    @Test
    void testPart2() {
        assertEquals(467835, Day3.part2(input));
    }
    
    @Test
    void testIsNextToASymbol() {
        Set<Point> symbols = new HashSet<>(); 
        symbols.add(new Point(3, 1));
        symbols.add(new Point(6, 3));
        symbols.add(new Point(3, 4));
        symbols.add(new Point(5, 5));
        symbols.add(new Point(3, 8));
        symbols.add(new Point(5, 8));
        assertTrue(Day3.isNextToASymbol(new Point(2, 0), symbols));
        assertFalse(Day3.isNextToASymbol(new Point(5, 0), symbols));
    }
}
