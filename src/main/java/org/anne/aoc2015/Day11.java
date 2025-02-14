package org.anne.aoc2015;

import org.anne.common.Day;

import java.util.HashSet;
import java.util.Set;

public class Day11 extends Day {
    public static void main(String[] args) {
        new Day11().run();
    }

    @Override
    public void execute() {
        setName("Corporate Policy");
        String input = readFileOneLine();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static String part1(String input) {
        return getNextPassword(input);
    }

    public static String part2(String input) {
        return getNextPassword(getNextPassword(input));
    }

    private static String getNextPassword(String password) {
        while (true){
            password = increment(password);
            if (containsNoConfusingLetters(password) && containsTwoPairs(password) && containsStraight(password)) {
                return password;
            }
        }
    }

    private static boolean containsNoConfusingLetters(String password) {
        return password.indexOf('i') == -1 && password.indexOf('o') == -1 && password.indexOf('l') == -1;
    }

    private static boolean containsTwoPairs(String password) {
        Set<Character> pairs = new HashSet<>();
        for (int i = 0; i < password.length() - 1; i++) {
            if (password.charAt(i) == password.charAt(i + 1)) {
                pairs.add(password.charAt(i));
                i++;
            }
        }
        return pairs.size() >= 2;
    }

    private static boolean containsStraight(String password) {
        for (int i = 0; i < password.length() - 2; i++) {
            if (password.charAt(i) + 1 == password.charAt(i + 1) && password.charAt(i) + 2 == password.charAt(i + 2)) {
                return true;
            }
        }
        return false;
    }

    private static String increment(String password) {
        char[] chars = password.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == 'z') {
                chars[i] = 'a';
            } else {
                chars[i]++;
                break;
            }
        }
        return new String(chars);
    }
}
