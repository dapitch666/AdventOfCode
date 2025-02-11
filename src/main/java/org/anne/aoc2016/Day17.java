package org.anne.aoc2016;

import org.anne.common.Day;
import org.anne.common.Direction;
import org.anne.common.GridHelper;
import org.anne.common.HashUtils;

import java.awt.*;
import java.security.MessageDigest;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.anne.common.HashUtils.bytesToHex;

public class Day17 extends Day {
    public static void main(String[] args) {
        new Day17().run();
    }

    @Override
    public void execute() {
        setName("Two Steps Forward");
        String input = readFileOneLine();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    private static final MessageDigest md5 = HashUtils.getMd5Instance();

    public static String part1(String input) {
        return charListToString(bfs(input, new Point(0, 0), new Point(3, 3), true));
    }

    public static int part2(String input) {
        return bfs(input, new Point(0, 0), new Point(3, 3), false).size();
    }

    private static List<Character> bfs(String input, Point start, Point end, boolean shortest) {
        List<Character> path = new ArrayList<>();
        Deque<State> queue = new LinkedList<>();
        queue.add(new State(start, new ArrayList<>()));

        while (!queue.isEmpty()) {
            State current = queue.poll();
            for (Direction dir : getOpenDoors(input, current.dirs)) {
                Point next = dir.move(current.point);
                if (GridHelper.isValidPoint(next, 4)) {
                    List<Character> newDirs = new ArrayList<>(current.dirs);
                    newDirs.add(dir.getArrowChar());
                    if (next.equals(end)) {
                        if (shortest) {
                            return newDirs;
                        }
                        path = newDirs;
                    } else {
                        queue.add(new State(next, newDirs));
                    }
                }
            }
        }
        return path;
    }

    private static List<Direction> getOpenDoors(String input, List<Character> path) {
        String hex = bytesToHex(md5.digest((input + charListToString(path)).getBytes()));
        List<Direction> result = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (hex.charAt(i) >= 'b' && hex.charAt(i) <= 'f') {
                result.add(Direction.fromArrowChar("UDLR".charAt(i)));
            }
        }
        return result;
    }

    private static String charListToString(List<Character> chars) {
        return chars.stream().map(String::valueOf).collect(Collectors.joining());
    }

    record State(Point point, List<Character> dirs) {
    }
}
