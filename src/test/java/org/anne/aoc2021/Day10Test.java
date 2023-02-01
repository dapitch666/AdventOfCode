package org.anne.aoc2021;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day10Test {
    final List<String> input = List.of(
            "[({(<(())[]>[[{[]{<()<>>",
            "[(()[<>])]({[<{<<[]>>(",
            "{([(<{}[<>[]}>{[]{[(<()>",
            "(((({<>}<{<{<>}{[]{[]{}",
            "[[<[([]))<([[{}[[()]]]",
            "[{[{({}]{}}([{[{{{}}([]",
            "{<[[]]>}<{[{[{[]{()[[[]",
            "[<(<(<(<{}))><([]([]()",
            "<{([([[(<>()){}]>(<<{{",
            "<{([{{}}[<[[[<>{}]]]>[]]"
    );

    @Test
    void testPart1() {
        assertEquals(0, Day10.getCorruptedScore("()"));
        assertEquals(0, Day10.getCorruptedScore("[<>({}){}[([])<>]]"));
        assertEquals(3, Day10.getCorruptedScore("<([]){()}[{}])"));
        assertEquals(1197, Day10.getCorruptedScore("{([(<{}[<>[]}>{[]{[(<()>"));
        assertEquals(26397, Day10.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(0L, Day10.getIncompleteScore("([)"));
        assertEquals(294L, Day10.getIncompleteScore("<{([{{}}[<[[[<>{}]]]>[]]"));
        assertEquals(288957, Day10.part2(input));
    }
}