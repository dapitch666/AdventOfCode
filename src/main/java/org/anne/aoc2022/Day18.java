package org.anne.aoc2022;

import org.anne.common.Day;
import org.anne.common.Utils;

import java.lang.invoke.MethodHandles;
import java.util.*;

public class Day18 extends Day {
    public static void main(String[] args) {
        Day day = new Day18();
        List<String> input = day.readFile();
        day.setPart1(part1(input));
        day.setPart2(part2(input));
        day.printParts();
    }

    public static long part1(List<String> input) {
        List<Cube> cubes = getCubes(input);
        return (cubes.size() * 6L) - cubes.stream()
                                        .flatMap(c -> c.neighbors().stream())
                                        .filter(cubes::contains)
                                        .count();
    }

    public static long part2(List<String> input) {
        List<Cube> cubes = getCubes(input);
        Deque<Cube> queue = new LinkedList<>();

        Cube start = cubes.stream()
                .min(Comparator.comparingInt(o -> o.x + o.y + o.z))
                .orElseThrow()
                .neighbors()
                .stream()
                .filter(c -> !cubes.contains(c))
                .findFirst()
                .orElseThrow();

        queue.add(start);
        HashSet<Cube> air = new HashSet<>();
        while(queue.size() > 0) {
            Cube current = queue.poll();
            air.add(current);

            for(Cube neighbor : current.neighbors()) {
                if(air.contains(neighbor) || cubes.contains(neighbor) || queue.contains(neighbor)
                    || cubes.stream().mapToInt(c -> c.manhattanDistance(neighbor)).min().orElseThrow() > 2) {
                    continue;
                }
                queue.add(neighbor);
            }
        }
        return air.stream()
                .flatMap(c -> c.neighbors().stream())
                .filter(cubes::contains)
                .count();
    }

    public record Cube (int x, int y, int z) {
        List<Cube> neighbors () {
            List<Cube> neighbors = new ArrayList<>();
            neighbors.add(new Cube(x-1, y, z));
            neighbors.add(new Cube(x+1, y, z));
            neighbors.add(new Cube(x, y-1, z));
            neighbors.add(new Cube(x, y+1, z));
            neighbors.add(new Cube(x, y, z-1));
            neighbors.add(new Cube(x, y, z+1));
            return neighbors;
        }
        int manhattanDistance(Cube other) {
            return Math.abs(this.x - other.x) + Math.abs(this.y - other.y) + Math.abs(this.z - other.z);
        }
    }

    private static List<Cube> getCubes(List<String> input) {
        List<Cube> cubes = new ArrayList<>();
        for (String line : input) {
            String[] params = line.split(",");
            cubes.add(new Cube(Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[2])));
        }
        return cubes;
    }
}
