package org.anne.aoc2023;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day10Test {

    List<String> input = new ArrayList<>(Arrays.asList(
            "-L|F7",
            "7S-7|",
            "L|7||",
            "-L-J|",
            "L|-JF"
    ));

    List<String> input2 = new ArrayList<>(Arrays.asList(
            "7-F7-",
            ".FJ|7",
            "SJLL7",
            "|F--J",
            "LJ.LJ"
    ));

    List<String> input3 = new ArrayList<>(Arrays.asList(
            "...........",
            ".S-------7.",
            ".|F-----7|.",
            ".||.....||.",
            ".||.....||.",
            ".|L-7.F-J|.",
            ".|..|.|..|.",
            ".L--J.L--J.",
            "..........."
    ));

    List<String> input3bis = new ArrayList<>(Arrays.asList(
            "..........",
            ".S------7.",
            ".|F----7|.",
            ".||....||.",
            ".||....||.",
            ".|L-7F-J|.",
            ".|..||..|.",
            ".L--JL--J.",
            ".........."
    ));

    List<String> input4 = new ArrayList<>(Arrays.asList(
            ".F----7F7F7F7F-7....",
            ".|F--7||||||||FJ....",
            ".||.FJ||||||||L7....",
            "FJL7L7LJLJ||LJ.L-7..",
            "L--J.L7...LJS7F-7L7.",
            "....F-J..F7FJ|L7L7L7",
            "....L7.F7||L7|.L7L7|",
            ".....|FJLJ|FJ|F7|.LJ",
            "....FJL-7.||.||||...",
            "....L---J.LJ.LJLJ..."
    ));
    
    List<String> input5 = new ArrayList<>(Arrays.asList(
            "FF7FSF7F7F7F7F7F---7",
            "L|LJ||||||||||||F--J",
            "FL-7LJLJ||||||LJL-77",
            "F--JF--7||LJLJ7F7FJ-",
            "L---JF-JLJ.||-FJLJJ7",
            "|F|F-JF---7F7-L7L|7|",
            "|FFJF7L7F-JF7|JL---7",
            "7-L-JL7||F7|L7F-7F7|",
            "L.L7LFJ|||||FJL7||LJ",
            "L7JLJL-JLJLJL--JLJ.L"
    ));
    
    @Test
    void testPart1() {
        assertEquals(4, Day10.part1(input));
        assertEquals(8, Day10.part1(input2));
    }

    @Test
    void testPart2() {
        assertEquals(4, Day10.part2(input3bis));
        assertEquals(4, Day10.part2(input3));
        assertEquals(8, Day10.part2(input4));
        assertEquals(10, Day10.part2(input5));
    }
 }
