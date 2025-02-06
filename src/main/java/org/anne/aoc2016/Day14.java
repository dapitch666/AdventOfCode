package org.anne.aoc2016;

import org.anne.common.Day;
import org.anne.common.HashUtils;

import java.security.MessageDigest;
import java.util.ArrayDeque;
import java.util.Deque;

public class Day14 extends Day {
    public static void main(String[] args) {
        new Day14().run();
    }

    @Override
    public void execute() {
        setName("One-Time Pad");
        String input = readFileOneLine();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    private static final MessageDigest md5 = HashUtils.getMd5Instance();

    public static int part1(String input) {
        return get64thPadKeyIndex(input, 0);
    }

    public static int part2(String input) {
        return get64thPadKeyIndex(input, 2016);
    }

    private static int get64thPadKeyIndex(String salt, int loops) {
        int i = 0, n = 0;
        Deque<String> queue = new ArrayDeque<>();

        while (i < 64) {
            while (queue.size() <= 1000) {
                queue.add(hash(salt, n++, loops));
            }
            Character c = findThreeInARow(queue.pollFirst());
            if (c != null && findFiveRepeats(queue, c)) {
                i++;
            }
        }
        return n - queue.size() - 1;
    }

    private static String hash(String salt, int n, int loops) {
        String input = salt + n;
        String result = HashUtils.bytesToHex(md5.digest(input.getBytes()));
        for (int i = 0; i < loops; i++) {
            result = HashUtils.bytesToHex(md5.digest(result.getBytes()));
        }
        return result;
    }

    private static Character findThreeInARow(String s) {
        for (int i = 0; i < s.length() - 2; i++) {
            if (s.charAt(i) == s.charAt(i + 1) && s.charAt(i + 1) == s.charAt(i + 2)) {
                return s.charAt(i);
            }
        }
        return null;
    }

    private static boolean findFiveRepeats(Deque<String> hashes, char c) {
        String quintuple = String.valueOf(c).repeat(5);
        for (String h : hashes) {
            if (h.contains(quintuple)) {
                return true;
            }
        }
        return false;
    }
}
