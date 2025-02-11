package org.anne.aoc2018;


import org.anne.common.Day;
import org.anne.common.Direction;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import static java.util.Comparator.*;

public class Day15 extends Day {
    public static void main(String[] args) {
        new Day15().run();
    }

    @Override
    public void execute() {
        setName("Beverage Bandits");
        List<String> input = readFile();
        setPart1(part1(input));
        setPart2(part2(input));
    }

    public static int part1(List<String> input) {
        Predicate<List<Unit>> victoryCondition = units -> units.stream()
                .filter(Unit::isAlive)
                .map(u -> u.type)
                .distinct()
                .count() == 1;
        return runBattle(input, 0, victoryCondition, units -> false);
    }

    public static int part2(List<String> input) {
        int boost = 1;
        int elves = (int) input.stream().flatMapToInt(String::chars).filter(c -> c == 'E').count();
        Predicate<List<Unit>> exitCondition = units -> units.stream().filter(Unit::isElf).count() < elves;
        Predicate<List<Unit>> victoryCondition = units -> units.stream().noneMatch(u -> u.isAlive() && !u.isElf());
        while (true) {
            int result = runBattle(input, boost, victoryCondition, exitCondition);
            if (result > 0) return result;
            boost++;
        }
    }

    private static int runBattle(List<String> input, int boost, Predicate<List<Unit>> victoryCondition, Predicate<List<Unit>> exitCondition) {
        var grid = new int[input.size()][input.size()];
        List<Unit> units = new ArrayList<>();
        getData(input, grid, units, boost);
        int rounds = 0;
        while (true) {
            if (exitCondition.test(units)) return 0;
            units.sort(Comparator.comparingInt((Unit u) -> u.position.y).thenComparingInt(u -> u.position.x));
            for (Unit unit : units) {
                if (unit.isAlive() && unit.takeTurn(grid, units) && victoryCondition.test(units)) {
                    return units.stream().filter(Unit::isAlive).mapToInt(u -> u.hp).sum() * rounds;
                }
            }
            units.removeIf(unit -> !unit.isAlive());
            rounds++;
        }
    }

    private static Point bestMove(Point position, List<Unit> enemies, int[][] grid) {
        List<List<Point>> paths = enemies.stream()
                .flatMap(enemy -> getAdjacent(enemy.position, grid, '.').stream())
                .map(each -> bfs(position, each, grid))
                .filter(path -> !path.isEmpty())
                .collect(Collectors.toList());

        if (paths.isEmpty()) return null;

        int min = paths.stream()
                .mapToInt(List::size)
                .min().orElse(0);

        if (min == 0) return null;

        Point target = paths.stream()
                .filter(path -> path.size() == min)
                .map(List::getLast)
                .min(comparing(Point::getY).thenComparing(Point::getX))
                .orElse(null);

        if (target == null) return null;

        List<Point> adj = getAdjacent(position, grid, '.');
        paths = adj.stream()
                .map(each -> bfs(target, each, grid))
                .filter(path -> !path.isEmpty())
                .toList();

        int min2 = paths.stream()
                .mapToInt(List::size)
                .min().orElse(0);

        return paths.stream()
                .filter(path -> path.size() == min2)
                .map(List::getLast)
                .min(comparing(Point::getY).thenComparing(Point::getX))
                .orElse(null);
    }

    private static List<Point> bfs(Point start, Point target, int[][] grid) {
        Queue<Point> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Point[][] parents = new Point[grid.length][grid[0].length];

        queue.add(start);
        visited[start.y][start.x] = true;

        while (!queue.isEmpty()) {
            Point curr = queue.poll();
            if (curr.equals(target)) {
                List<Point> path = new ArrayList<>();
                for (Point p = target; !p.equals(start); p = parents[p.y][p.x]) {
                    path.add(p);
                }
                path.add(start);
                Collections.reverse(path);
                return path;
            }

            for (Point adjacent : getAdjacent(curr, grid, '.')) {
                if (!visited[adjacent.y][adjacent.x]) {
                    queue.add(adjacent);
                    visited[adjacent.y][adjacent.x] = true;
                    parents[adjacent.y][adjacent.x] = curr;
                }
            }
        }
        return Collections.emptyList();
    }

    private static List<Point> getAdjacent(Point point, int[][] grid, char c) {
        List<Point> adjacent = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            Point n = direction.move(point);
            if (grid[n.x][n.y] == c) {
                adjacent.add(n);
            }
        }
        return adjacent;
    }

    private static void getData(List<String> input, int[][] grid, List<Unit> units, int boost) {
        for (int y = 0; y < input.size(); y++) {
            String line = input.get(y);
            for (int x = 0; x < line.length(); x++) {
                char c = line.charAt(x);
                if (c == 'G' || c == 'E') {
                    units.add(new Unit(new Point(x, y), c, boost));
                }
                grid[x][y] = c;
            }
        }
    }

    private static class Unit {
        Point position;
        final char type;
        int hp = 200;
        int power = 3;

        int getHp() { return hp; }
        boolean isAlive() { return hp > 0; }
        boolean isElf() { return type == 'E'; }
        char enemyType() { return type == 'G' ? 'E' : 'G'; }

        Unit(Point position, char type, int boost) {
            this.position = position;
            this.type = type;
            if (type == 'E') power += boost;
        }

        Unit getAdjacentEnemy(List<Unit> units, int[][] grid) {
            List<Point> adjacentPoints = getAdjacent(position, grid, enemyType());
            if (adjacentPoints.isEmpty()) return null;

            List<Unit> adjacentEnemies = units.stream()
                    .filter(u -> u.isAlive() && adjacentPoints.contains(u.position))
                    .toList();
            if (adjacentEnemies.isEmpty()) return null;

            if (adjacentEnemies.size() == 1) return adjacentEnemies.getFirst();

            return adjacentEnemies.stream()
                    .min(comparingInt(Unit::getHp)
                            .thenComparingInt(u -> u.position.y)
                            .thenComparingInt(u -> u.position.x))
                    .orElse(null);
        }

        boolean takeTurn(int[][] grid, List<Unit> units) {
            if (!isAlive()) return false;

            List<Unit> enemies = units.stream()
                    .filter(unit -> unit.type != type)
                    .filter(Unit::isAlive)
                    .toList();
            if (enemies.isEmpty()) return true; // Battle is over

            Unit enemy = getAdjacentEnemy(units, grid);
            if (enemy == null) {
                // move
                Point moveTo = bestMove(position, enemies, grid);
                if (moveTo != null) {
                    grid[position.x][position.y] = '.';
                    grid[moveTo.x][moveTo.y] = type;
                    position = moveTo;
                }
                enemy = getAdjacentEnemy(units, grid);
                if (enemy == null) return false;
            }
            // attack
            enemy.hp -= power;
            if (!enemy.isAlive()) {
                grid[enemy.position.x][enemy.position.y] = '.';
            }
            return false;
        }
    }
}