package org.anne.aoc2016;

import org.anne.common.Day;

import java.util.List;
import java.util.regex.Pattern;

public class Day23 extends Day {
    public static void main(String[] args) {
        new Day23().run();
    }

    @Override
    public void execute() {
        setName("Safe Cracking");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(List<String> input) {
        AssemBunny assemBunny = new AssemBunny(input);
        assemBunny.setRegister('a', 7);
        assemBunny.run();
        return assemBunny.getRegisterA();
    }

    public static int part2(List<String> input) {
        int factor1 = 0, factor2 = 0;
        Pattern pattern1 = Pattern.compile("cpy (\\d+) c");
        Pattern pattern2 = Pattern.compile("jnz (\\d+) d");
        for (String line : input) {
            if (pattern1.matcher(line).matches()) {
                factor1 = Integer.parseInt(line.split(" ")[1]);
            } else if (pattern2.matcher(line).matches()) {
                factor2 = Integer.parseInt(line.split(" ")[1]);
            }
        }
        return factorial(12) + factor1 * factor2;
    }

    public static int factorial(int n) {
        if (n == 0) return 1;
        return n * factorial(n - 1);
    }
}
