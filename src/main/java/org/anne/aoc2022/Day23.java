package org.anne.aoc2022;

import org.anne.common.Day;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day23 extends Day {
    public static void main(String[] args) {
        new Day23().run();
    }

    @Override
    public void execute() {
        setName("Unstable Diffusion");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
        printParts();
    }

    public static int part1(List<String> input) {
        var dirs = new LinkedList<>(Arrays.asList(Direction.N, Direction.S, Direction.W, Direction.E));
        var elves = scanTheGrove(input);

        for(int i = 0; i < 10; i++) {
            elves = round(elves, dirs);
            dirs.add(dirs.removeFirst());
        }

        int minX = elves.stream().map(x -> x.x).min(Integer::compare).orElseThrow();
        int maxX = elves.stream().map(x -> x.x).max(Integer::compare).orElseThrow();
        int minY = elves.stream().map(x -> x.y).min(Integer::compare).orElseThrow();
        int maxY = elves.stream().map(x -> x.y).max(Integer::compare).orElseThrow();

        return ((maxX - minX + 1) * (maxY - minY + 1)) - elves.size();
    }

    public static int part2(List<String> input) {
        var directions = new LinkedList<>(Arrays.asList(Direction.N, Direction.S, Direction.W, Direction.E));
        var elves = scanTheGrove(input);

        int round = 0;
        while(true) {
            round++;
            var newElves = round(elves, directions);
            if(elves.equals(newElves)) {
                break;
            }
            elves = newElves;
            directions.add(directions.removeFirst());
        }
        return round;
    }

    private static Set<Point> round(Set<Point> elves, LinkedList<Direction> directions) {
        var movements = new HashMap<Point, Point>();
        for(Point elf : elves) {
            List<Point> adj = getSurrounding(elf);
            adj.retainAll(elves);
            if(adj.isEmpty())
                continue;
            for(Direction d : directions) {
                List<Point> checks = d.getDiagonals(elf);
                if(checks.stream().noneMatch(adj::contains)) {
                    movements.put(elf, d.applyTo(elf));
                    break;
                }
            }
        }
        var newElves = new HashSet<Point>();
        var nbElvesByPoint = movements.values().stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for(Point elf : elves) {
            if(nbElvesByPoint.getOrDefault(movements.getOrDefault(elf,null),2L) > 1L)
                newElves.add(elf);
            else
                newElves.add(movements.get(elf));
        }
        return newElves;
    }

    private static Set<Point> scanTheGrove(List<String> input) {
        var elves = new HashSet<Point>();
        for(int y = 0; y < input.size(); y++) {
            String line = input.get(y);
            for(int x = 0; x < line.length(); x++) {
                if(line.charAt(x) == '#')
                    elves.add(new Point(x, y));
            }
        }
        return elves;
    }

    private static List<Point> getSurrounding(Point point) {
        return Stream.of(-1, 0, 1)
                .flatMap(y -> Stream.of(-1, 0, 1)
                        .filter(x -> !(x == 0 && y == 0))
                        .map(x -> new Point(point.x + x, point.y + y)))
                .collect(Collectors.toList());
    }

    public enum Direction {
        N(0, -1),
        S(0, 1),
        W(-1, 0),
        E(1, 0);

        public final int x;
        public final int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public List<Point> getDiagonals(Point point) {
            return switch (this) {
                case N -> Arrays.asList(Direction.N.applyTo(point),
                        Direction.N.applyTo(Direction.E.applyTo(point)),
                        Direction.N.applyTo(Direction.W.applyTo(point)));
                case S -> Arrays.asList(Direction.S.applyTo(point),
                        Direction.S.applyTo(Direction.E.applyTo(point)),
                        Direction.S.applyTo(Direction.W.applyTo(point)));
                case E -> Arrays.asList(Direction.E.applyTo(point),
                        Direction.N.applyTo(Direction.E.applyTo(point)),
                        Direction.S.applyTo(Direction.E.applyTo(point)));
                case W -> Arrays.asList(Direction.W.applyTo(point),
                        Direction.N.applyTo(Direction.W.applyTo(point)),
                        Direction.S.applyTo(Direction.W.applyTo(point)));
            };
        }

        public Point applyTo(Point point) {
            Point ret = new Point(point);
            ret.translate(this.x, this.y);
            return ret;
        }
    }
}
