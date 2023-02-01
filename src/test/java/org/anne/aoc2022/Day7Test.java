package org.anne.aoc2022;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.anne.aoc2022.Day7.getRoot;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day7Test {
    List<String> input = Arrays.asList(
            "$ cd /",
            "$ ls",
            "dir a",
            "14848514 b.txt",
            "8504156 c.dat",
            "dir d",
            "$ cd a",
            "$ ls",
            "dir e",
            "29116 f",
            "2557 g",
            "62596 h.lst",
            "$ cd e",
            "$ ls",
            "584 i",
            "$ cd ..",
            "$ cd ..",
            "$ cd d",
            "$ ls",
            "4060174 j",
            "8033020 d.log",
            "5626152 d.ext",
            "7214296 k"
          );

    List<String> input2 = Arrays.asList(
            "$ cd /",
            "$ ls",
            "dir a",
            "$ cd a",
            "$ ls",
            "400 file4",
            "dir b",
            "$ cd b",
            "$ ls",
            "dir c",
            "500 file3",
            "$ cd c",
            "$ ls",
            "50 file1",
            "150 file2"
    );

    Day7.Directory root = getRoot(input);
    Day7.Directory root2 = getRoot(input2);

    @Test
    void part1() {
        assertEquals(95437, Day7.part1(root));
        assertEquals(3100, Day7.part1(root2));
    }

    @Test
    void part2() {
        assertEquals(24933642, Day7.part2(root));
    }
}
