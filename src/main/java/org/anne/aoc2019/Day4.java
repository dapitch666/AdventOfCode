package org.anne.aoc2019;

import org.anne.common.Day;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day4 extends Day {
    public static void main(String[] args) {
        new Day4().run();
    }

    @Override
    public void execute() {
        setName("Secure Container");
        List<Integer> input = readFileGetAllInts();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    static int part1(List<Integer> input) {
        int validPasswords = 0;
        for (int i = input.get(0); i <= input.get(1); i++) {
            String password = Integer.toString(i);
            if (isOrdered(password) && hasIdenticalAdjacentDigits(password)) {
                validPasswords++;
            }
        }
        return validPasswords;
    }

    static int part2(List<Integer> input) {
        int validPasswords = 0;
        for (int i = input.get(0); i <= input.get(1); i++) {
            String password = Integer.toString(i);
            if (isOrdered(password) && hasTwoIdenticalAdjacentDigits(password)) {
                validPasswords++;
            }
        }
        return validPasswords;
    }

    static boolean hasIdenticalAdjacentDigits(String password) {
        boolean identical = false;
        for (int j = 0; j < 5; j++) {
            char c1 = password.charAt(j);
            char c2 = password.charAt(j+1);
            if (c1 == c2) {
                identical = true;
                break;
            }
        }
        return identical;
    }

    static boolean hasTwoIdenticalAdjacentDigits(String password) {
        Map<Character, Integer> charCount = characterCount(password);
        return charCount.containsValue(2);
    }

    static boolean isOrdered(String str) {
        char[] strArray = str.toCharArray();
        Arrays.sort(strArray);
        return (str.equals(String.valueOf(strArray)));
    }

    private static Map<Character, Integer> characterCount(String str) {
        Map<Character, Integer> frequencies = new HashMap<>();
        for (char c : str.toCharArray()) {
            frequencies.merge(c,1, Integer::sum);
        }
        return frequencies;
    }
}
