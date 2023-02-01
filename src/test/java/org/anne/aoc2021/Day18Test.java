package org.anne.aoc2021;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day18Test {
    final List<String> input = List.of(
            "[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]",
            "[[[5,[2,8]],4],[5,[[9,9],0]]]",
            "[6,[[[6,2],[5,6]],[[7,6],[4,7]]]]",
            "[[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]",
            "[[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]",
            "[[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]",
            "[[[[5,4],[7,7]],8],[[8,3],8]]",
            "[[9,3],[[9,9],[6,[4,9]]]]",
            "[[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]",
            "[[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]"
    );

    @Test
    void testPart1() {
        assertEquals(4140, Day18.part1(input));
    }

    @Test
    void testPart2() {
        assertEquals(3993, Day18.part2(input));
    }

    @Test
    void testPairs() {
        System.out.println(Day18.part1(List.of("[[[[1,2],[3,4]],[[5,6],[7,8]]],9]")));
    }

    @Test
    void testReduce() {
        Day18.Pair pair = Day18.parseString("[7,[6,[5,[4,[3,2]]]]]");
        pair.reduce();
        assertEquals("[7,[6,[5,[7,0]]]]", pair.toString());

        pair = Day18.parseString("[[[[[9,8],1],2],3],4]");
        pair.reduce();
        assertEquals("[[[[0,9],2],3],4]", pair.toString());

        pair = Day18.parseString("[[6,[5,[4,[3,2]]]],1]");
        pair.reduce();
        assertEquals("[[6,[5,[7,0]]],3]", pair.toString());

        pair = Day18.parseString("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]");
        pair.reduce();
        assertEquals("[[3,[2,[8,0]]],[9,[5,[7,0]]]]", pair.toString());

        pair = Day18.parseString("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]");
        pair.reduce();
        assertEquals("[[3,[2,[8,0]]],[9,[5,[7,0]]]]", pair.toString());

        pair = Day18.parseString("[[10,7],[4,9]]");
        pair.reduce();
        assertEquals("[[[5,5],7],[4,9]]", pair.toString());
    }

    @Test
    void testAddition() {
        Day18.Pair pair = Day18.addition(Day18.parseString("[1,2]"), Day18.parseString("[[3,4],5]"));
        assertEquals("[[1,2],[[3,4],5]]", pair.toString());
    }

    @Test
    void testMagnitude() {
        assertEquals(29, Day18.parseString("[9,1]").magnitude());
        assertEquals(129, Day18.parseString("[[9,1],[1,9]]").magnitude());
        assertEquals(1137, Day18.parseString("[[[[5,0],[7,4]],[5,5]],[6,6]]").magnitude());
    }
}