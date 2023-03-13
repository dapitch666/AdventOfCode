package org.anne.aoc2019;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day18Test {

    List<String> input1 = List.of(
            "#########",
            "#b.A.@.a#",
            "#########"
    );

    List<String> input2 = List.of(
            "########################",
            "#f.D.E.e.C.b.A.@.a.B.c.#",
            "######################.#",
            "#d.....................#",
            "########################"
    );

    List<String> input3 = List.of(
            "########################",
            "#...............b.C.D.f#",
            "#.######################",
            "#.....@.a.B.c.d.A.e.F.g#",
            "########################"
    );

    List<String> input4 = List.of(
            "#################",
            "#i.G..c...e..H.p#",
            "########.########",
            "#j.A..b...f..D.o#",
            "########@########",
            "#k.E..a...g..B.n#",
            "########.########",
            "#l.F..d...h..C.m#",
            "#################"
    );

    List<String> input5 = List.of(
            "########################",
            "#@..............ac.GI.b#",
            "###d#e#f################",
            "###A#B#C################",
            "###g#h#i################",
            "########################"
    );

    @Test
    void part1() {
        assertEquals(8, Day18.part1(input1));
        assertEquals(86, Day18.part1(input2));
        assertEquals(132, Day18.part1(input3));
        assertEquals(136, Day18.part1(input4));
        assertEquals(81, Day18.part1(input5));
    }

    List<String> input6 = List.of(
            "###############",
            "#d.ABC.#.....a#",
            "######...######",
            "######.@.######",
            "######...######",
            "#b.....#.....c#",
            "###############"
    );

    List<String> input7 = List.of(
            "#############",
            "#g#f.D#..h#l#",
            "#F###e#E###.#",
            "#dCba...BcIJ#",
            "#####.@.#####",
            "#nK.L...G...#",
            "#M###N#H###.#",
            "#o#m..#i#jk.#",
            "#############"
    );

    @Test
    void part2() {
        assertEquals(24, Day18.part2(new ArrayList<>(input6)));
        assertEquals(70, Day18.part2(new ArrayList<>(input7)));
    }
}