package org.anne.aoc2017;

import org.anne.common.Day;
import org.anne.common.Direction;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Day22 extends Day {
    public static void main(String[] args) {
        new Day22().run();
    }

    @Override
    public void execute() {
        setName("Sporifica Virus");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(List<String> input) {
        Set<Point> infected = new HashSet<>();
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length(); x++) {
                if (input.get(y).charAt(x) == '#') {
                    infected.add(new Point(x, y));
                }
            }
        }
        Point virus = new Point(input.getFirst().length() / 2, input.size() / 2);
        Direction direction = Direction.NORTH;
        int infections = 0;
        for (int i = 0; i < 10000; i++) {
            if (infected.contains(virus)) {
                direction = direction.rotate90(true);
                infected.remove(virus);
            } else {
                direction = direction.rotate90(false);
                infected.add(new Point(virus));
                infections++;
            }
            virus.translate(direction.dx, direction.dy);
        }
        return infections;
    }

    public static int part2(List<String> input) {
        Map<Point, Character> infected = new HashMap<>();
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length(); x++) {
                if (input.get(y).charAt(x) == '#') {
                    infected.put(new Point(x, y), 'i');
                }
            }
        }
        Point virus = new Point(input.getFirst().length() / 2, input.size() / 2);
        Direction direction = Direction.NORTH;
        int infections = 0;
        for (int i = 0; i < 10000000; i++) {
            switch (infected.getOrDefault(virus, 'c')) {
                case 'i':
                    infected.put(virus, 'f');
                    direction = direction.rotate90(true);
                    break;
                case 'f':
                    infected.remove(virus);
                    direction = direction.reverse();
                    break;
                case 'w':
                    infected.put(virus, 'i');
                    infections++;
                    break;
                case 'c':
                    infected.put(new Point(virus), 'w');
                    direction = direction.rotate90(false);
                    break;
            }
            virus.translate(direction.dx, direction.dy);
        }
        return infections;
    }
}
